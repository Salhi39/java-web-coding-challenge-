package com.hiddenfounders.webcc.controller;

import com.hiddenfounders.webcc.model.Shop;
import com.hiddenfounders.webcc.model.Status;
import com.hiddenfounders.webcc.model.User;
import com.hiddenfounders.webcc.model.utility.Constants;
import com.hiddenfounders.webcc.model.utility.Location;
import com.hiddenfounders.webcc.service.database.MongoDBService;
import com.hiddenfounders.webcc.service.notification.Notifier;
import com.hiddenfounders.webcc.service.notification.Waiter;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
@RequestMapping("/")
public class HomeController {


    @Autowired
    MongoDBService mongoDBService;


    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String getAllShops() {
        JSONArray jsonArray = new JSONArray();
        List<Shop> shopList = mongoDBService.findAllShop();
        for (Shop shop : shopList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("_id", shop.getIdShop());
            jsonObject.put("picture", shop.getPicture());
            jsonObject.put("name", shop.getName());
            jsonObject.put("city", shop.getCity());
            jsonObject.put("location", shop.getLocation().getCoordinates());

            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }


    @RequestMapping(value = {"/not_commented_shop/{email}"}, method = RequestMethod.GET)
    public String getAllNotCommentedShops(@PathVariable String email) {
        email = convertHexToString(email);
        JSONArray jsonArray = new JSONArray();
        List<Shop> shopList = mongoDBService.findAllNotCommentedShop(email);
        for (Shop shop : shopList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("_id", shop.getIdShop());
            jsonObject.put("picture", shop.getPicture());
            jsonObject.put("name", shop.getName());
            jsonObject.put("city", shop.getCity());
            jsonObject.put("email", shop.getEmail());
            jsonObject.put("location", shop.getLocation().getCoordinates());

            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public String getAllUser() {

        JSONArray jsonArray = new JSONArray();
        List<User> userList = mongoDBService.findAllUser();
        for (User user : userList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("_id_user", user.getIdUser());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("email", user.getEmail());

            JSONArray jsonArrayStatusLiked = new JSONArray();
            JSONArray jsonArrayStatusDisliked = new JSONArray();

            List<Status> allStatus = new ArrayList<>();
            allStatus.addAll(user.getShopdisliked());
            allStatus.addAll(user.getShopdisliked());

            for (Status status : allStatus) {
                JSONObject jsonObjectStatus = new JSONObject();
                jsonObjectStatus.put("_id_status", status.getIdStatus());
                jsonObjectStatus.put("_id_shop", status.getIdShop());
                jsonObjectStatus.put("passed_time", status.getPassedTime());
                jsonObjectStatus.put("status", status.getStatus());

                if (status.getStatus() == Constants.STATUS.LIKE)
                    jsonArrayStatusLiked.put(jsonObjectStatus);
                else
                    jsonArrayStatusDisliked.put(jsonObjectStatus);
            }

            jsonObject.put("shopLiked", jsonArrayStatusLiked);
            jsonObject.put("shopDisLiked", jsonArrayStatusDisliked);


            jsonArray.put(jsonObject);
        }


        return jsonArray.toString();
    }


    @RequestMapping(value = {"/preferred_shops/{email}"}, method = RequestMethod.GET)
    public String getAllLikedStatus(@PathVariable String email) {
        email = convertHexToString(email);

        User user = mongoDBService.findUserByEmail(email);

        JSONArray jsonArray = new JSONArray();
        List<Shop> shopList = mongoDBService.findAllLikedShop(user.getIdUser());
        for (Shop shop : shopList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("_id", shop.getIdShop());
            jsonObject.put("picture", shop.getPicture());
            jsonObject.put("name", shop.getName());
            jsonObject.put("city", shop.getCity());
            jsonObject.put("email", shop.getEmail());
            jsonObject.put("location", shop.getLocation().getCoordinates());

            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    ///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
    @RequestMapping(value = {"/preferred_shops"}, method = RequestMethod.GET)
    public String getAllLikedShop() {
        String email = "1";

        User user = mongoDBService.findUserByEmail(email);

        JSONArray jsonArray = new JSONArray();
        List<Shop> shopList = mongoDBService.findAllLikedShop(user.getIdUser());
        for (Shop shop : shopList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("_id", shop.getIdShop());
            jsonObject.put("picture", shop.getPicture());
            jsonObject.put("name", shop.getName());
            jsonObject.put("city", shop.getCity());
            jsonObject.put("email", shop.getEmail());
            jsonObject.put("location", shop.getLocation().getCoordinates());

            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }


    @RequestMapping(value = {"/disliked_shops"}, method = RequestMethod.GET)
    public String getAlldislikedShop() {
        String email = "1";

        User user = mongoDBService.findUserByEmail(email);
        JSONArray jsonArray = new JSONArray();
        List<Shop> shopList = mongoDBService.findAllDislikedShop(user.getIdUser());
        for (Shop shop : shopList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("_id", shop.getIdShop());
            jsonObject.put("picture", shop.getPicture());
            jsonObject.put("name", shop.getName());
            jsonObject.put("city", shop.getCity());
            jsonObject.put("email", shop.getEmail());
            jsonObject.put("location", shop.getLocation().getCoordinates());

            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }


    ///////////////////////////////////////////////////////
    @RequestMapping(value = {"/start"}, method = RequestMethod.GET)
    public String start() {
        User user = mongoDBService.findUserByEmail("1");
        List<Status> statusList = mongoDBService.findAllStatusWhere(user.getIdUser(), Constants.STATUS.DISLIKE);
        mongoDBService.deleteStatus(statusList.get(0));
        if (!statusList.isEmpty()) {
            Status status = statusList.get(0);
            Waiter waiter = new Waiter(mongoDBService, status, user);
            Thread waiterThread = new Thread(waiter, "waiterThread");
            waiterThread.start();

            Notifier notifier = new Notifier(mongoDBService, status, user);
            Thread notifierThread = new Thread(notifier, "notifierThread");
            notifierThread.start();
        }
        return "start ..";
    }


    @RequestMapping(value = {"/shop"}, method = RequestMethod.GET)
    public String getAllUndesirableShops() {
        List<User> userList = mongoDBService.findAllUser();
        String str = "";//"<h1>SHOP</h1></br></br> ";
        for (User user : userList) {
            str += user.toString() + "</br></br>";
        }
        return str;
    }


    @RequestMapping(value = {"/check_login"}, method = RequestMethod.POST)
    public String checkUserLogin(@RequestBody String userJson) {
        JSONObject jsonObject = new JSONObject(userJson);
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");

        Constants.LOGIN_STATUS status = mongoDBService.checkPassword(email, password);

        return "{\"task\": \"check login for user " + email + " \", " +
                "\"status\": \"" + status + "\"}";
    }

    @RequestMapping(value = {"/clear_user"}, method = RequestMethod.GET)
    public String clear() {
        mongoDBService.deleteAllUsers();
        return "clear user";
    }

    @RequestMapping(value = {"/create_user"}, method = RequestMethod.POST)
    public String createUsers(@RequestBody String userJson) {
        JSONObject jsonObject = new JSONObject(userJson);
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");

        mongoDBService.createUser(new User(email, password, new ArrayList<>(), new ArrayList<>()));

        return "{\"task\": \"create user " + email + " \", " +
                "\"status\": \"200\"}";
    }


    @RequestMapping(value = {"/add_shop_as_liked"}, method = RequestMethod.POST)
    public String addShopToLikedList(@RequestBody String data) {
        JSONObject jsonObject = new JSONObject(data);
        String userEmail = jsonObject.getString("userEmail");
        ObjectId idShop = new ObjectId(jsonObject.getString("idShop"));

        mongoDBService.addShopToLikeList(userEmail, idShop);

        return "{\"task\": \"shop liked by " + userEmail + " \", " +
                "\"status\": \"200\"}";
    }

    @RequestMapping(value = {"/add_shop_as_disliked"}, method = RequestMethod.POST)
    public String addShopToDislikedList(@RequestBody String data) {
        JSONObject jsonObject = new JSONObject(data);
        String userEmail = jsonObject.getString("userEmail");
        ObjectId idShop = new ObjectId(jsonObject.getString("idShop"));

        mongoDBService.addShopToDislikeList(userEmail, idShop);

        return "{\"task\": \"shop disliked by " + userEmail + " \", " +
                "\"status\": \"200\"}";
    }


    @RequestMapping(value = {"/remove_from_liked_shop"}, method = RequestMethod.POST)
    public String removeShopFromLikedList(@RequestBody String data) {
        JSONObject jsonObject = new JSONObject(data);
        String userEmail = jsonObject.getString("userEmail");
        ObjectId idShop = new ObjectId(jsonObject.getString("idShop"));

        mongoDBService.removeShopFromList(userEmail, idShop, Constants.STATUS.LIKE);

        return "{\"task\": \"remove shop liked by " + userEmail + " \", " +
                "\"status\": \"200\"}";
    }

    @RequestMapping(value = {"/remove_from_disliked_shop"}, method = RequestMethod.POST)
    public String removeShopFromDislikedList(@RequestBody String data) {
        JSONObject jsonObject = new JSONObject(data);
        String userEmail = jsonObject.getString("userEmail");
        ObjectId idShop = new ObjectId(jsonObject.getString("idShop"));

        mongoDBService.removeShopFromList(userEmail, idShop, Constants.STATUS.DISLIKE);

        return "{\"task\": \"remove shop disliked by " + userEmail + " \", " +
                "\"status\": \"200\"}";
    }


    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public String createShops() {

        /*double latLong[]= {124.6682391, -17.8978304};
        Location loc = new Location(latLong);
        BasicDBObject loc2= new BasicDBObject("location",
                new BasicDBObject("type", "Point")
                        .append("coordinates", (latLong)));
        List<Status> shopListLike = new ArrayList<>();
        List<Status> shopListDislike = new ArrayList<>();
        Status status = new Status.StatusBuilder()
                .setStatus(Constants.STATUS.LIKE)
                .setIdShop("5a0c6711fb3aac66aafe26c6")
                .setPassedTime(new Date())
                .build();
        shopListLike.add(status);

        status = new Status.StatusBuilder()
                .setStatus(Constants.STATUS.LIKE)
                .setIdShop("5a0c6711fb3aac66aafe26cc")
                .setPassedTime(new Date())
                .build();

        shopListLike.add(status);


        status = new Status.StatusBuilder()
                .setStatus(Constants.STATUS.DISLIKE)
                .setIdShop("5a0c6755fb3aac66aafe26df")
                .setPassedTime(new Date())
                .build();

        shopListDislike.add(status);

        User user = new User.UserBuilder()
                .setEmail("m@m.com")
                .setPassword("ddddddddddddd")
                .setShopdisliked(shopListDislike)
                .setShopLiked(shopListLike)
                .build();
        mongoDBService.createUser(user);*/
        return "create";
    }


    /*
   Temporal method that can help me to send right string
   through GET
   (I had some problems with some characters when they are sending by GET method)
   So I convert them to HEX then to STRING

   JUST Temporal method (this will be handled using crypto algo)
    */
    private String convertHexToString(String hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < hex.length() - 1; i += 2) {

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char) decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }

}




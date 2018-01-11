package com.hiddenfounders.webcc.controller;

import com.hiddenfounders.webcc.model.Shop;
import com.hiddenfounders.webcc.model.Status;
import com.hiddenfounders.webcc.model.User;
import com.hiddenfounders.webcc.model.utility.Constants;
import com.hiddenfounders.webcc.model.utility.Location;
import com.hiddenfounders.webcc.service.MongoDBService;
import com.hiddenfounders.webcc.service.MongoDBServiceImp;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RestController
@RequestMapping("/")
public class HomeController {


    @Autowired
    MongoDBService mongoDBService;



    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String getAllShops() {

        return "index";
    }


    @RequestMapping(value = {"/shop" }, method = RequestMethod.GET)
    public String getAllUndesirableShops() {
        List<User> userList = mongoDBService.findAllUser();
        String str = "";//"<h1>SHOP</h1></br></br> ";
        for (User user: userList) {
            str += user.toString()+"</br></br>";
        }
        return str;
    }



    @RequestMapping(value = {"/create" }, method = RequestMethod.GET)
    public String createShops() {

        double latLong[]= {124.6682391, -17.8978304};
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
        mongoDBService.createUser(user);
        return "create";
    }
}
package com.hiddenfounders.webcc.service;

import com.hiddenfounders.webcc.model.Shop;
import com.hiddenfounders.webcc.model.Status;
import com.hiddenfounders.webcc.model.User;
import com.hiddenfounders.webcc.model.utility.Constants;
import com.hiddenfounders.webcc.repository.ShopRepository;
import com.hiddenfounders.webcc.repository.StatusRepository;
import com.hiddenfounders.webcc.repository.UserRepository;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohamed SALHI
 * @date Created on 12/26/17 - 11:43 PM
 * @package com.hiddenfounders.webcc.service
 */



@Service
public class MongoDBServiceImp implements MongoDBService {

    @Autowired
    @Qualifier(value = "userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier(value = "shopRepository")
    private ShopRepository shopRepository;


    @Autowired
    @Qualifier(value = "statusRepository")
    private StatusRepository statusRepository;



    //######################################
    //-------------- USER ----------------
    //######################################

    /**
     *
     * @param user
     * @return
     */
    @Override
    public User createUser(User user) {
        List<Status> likedStatus = new ArrayList<>();
        for (Status status:user.getShopLiked()) {
            likedStatus.add(createStatus(status));
        }

        List<Status> dislikedStatus = new ArrayList<>();
        for (Status status:user.getShopdisliked()) {
            dislikedStatus.add(createStatus(status));
        }

        User persist = new User.UserBuilder()
                    .setEmail(user.getEmail())
                    .setPassword(user.getPassword())
                    .setShopdisliked(dislikedStatus)
                    .setShopLiked(likedStatus )
                    .build();

        return userRepository.save(persist);
    }


    /**
     *
     * @param id
     */
    @Override
    public void deleteUser(ObjectId id) {
        userRepository.delete(id);

    }

    /**
     *
     * @return
     */
    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }


    /**
     *
     * @param id
     * @return
     */
    @Override
    public User findUserById(ObjectId id) {
        return userRepository.findOne(id);
    }

    public ResponseEntity updateUserPassword(ObjectId id, String passeword) {
        User user = userRepository.findOne(id);
        if(user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        user.setPassword(passeword);
        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    /**
     * @param email
     * @param password
     * @return
     */
    public Constants.LOGIN_STATUS checkPassword(String email, String password){
        List<User> userList =  userRepository.findAll();
        for (User user: userList) {
            if(user.getEmail().equals(email.trim())){
                if(BCrypt.checkpw(password, user.getPassword()))
                    return Constants.LOGIN_STATUS.SUCCESSFUL;
                else
                    return Constants.LOGIN_STATUS.WRONG_PASSWORD;
            }
        }

        return Constants.LOGIN_STATUS.WRONG_EMAIL;
    }



    public void deleteAllUsers(){
        userRepository.deleteAll();
    }





    //######################################
    //-------------- SHOP ----------------
    //######################################



    /**
     *
     * @param shop
     * @return
     */
    @Override
    public Shop createShop(Shop shop) {
        Shop persist = new Shop.ShopBuilder()
                .setName(shop.getName())
                .setEmail(shop.getEmail())
                .setPicture(shop.getPicture())
                .setCity(shop.getCity())
                .setLocation(shop.getLocation())
                .build();
        return shopRepository.save(shop);
    }


    /**
     *
     * @param id
     */
    @Override
    public void deleteShop(ObjectId id) {
        shopRepository.delete(id);
    }


    /**
     *
     * @return
     */
    @Override
    public List<Shop> findAllShop() {
        return shopRepository.findAll();
    }


    /**
     *
     * @param id
     * @return
     */
    @Override
    public Shop findShopById(ObjectId id) {
        return shopRepository.findOne(id);
    }


    /**
     *
     * @param idUser
     * @return
     */
    public List<Shop> findAllLikedShop(ObjectId idUser){
        List<Status> statusList = findAllStatusWhere(idUser, Constants.STATUS.LIKE);
        List<Shop> shopList = new ArrayList<>();

        for (Status status: statusList) {
            shopList.add(findShopById(status.getIdShop()));
        }

        return shopList;
    }


    /**
     *
     * @param idUser
     * @return
     */
    public List<Shop> findAllDislikedShop(ObjectId idUser){
        List<Status> statusList = findAllStatusWhere(idUser, Constants.STATUS.DISLIKE);
        List<Shop> shopList = new ArrayList<>();

        for (Status status: statusList) {
            shopList.add(findShopById(status.getIdShop()));
        }

        return shopList;
    }









    //######################################
    //-------------- STATUS ----------------
    //######################################


    /**
     *
     * @param status
     * @return
     */
    @Override
    public Status createStatus(Status status){
        Status persist = new Status.StatusBuilder()
                .setIdStatus(status.getIdStatus())
                .setStatus(status.getStatus())
                .setIdShop(status.getIdShop())
                .setPassedTime(status.getPassedTime())
                .build();
        return statusRepository.save(persist);
    }


    /**
     *
     * @param status
     */
    @Override
    public void deleteStatus(Status status){
        statusRepository.delete(status);
    }


    /**
     *
     * @param idUser
     * @param status
     * @return
     */
    @Override
    public List<Status> findAllStatusWhere(ObjectId idUser, Constants.STATUS status){
        User user = userRepository.findOne(idUser);
        if(status == Constants.STATUS.LIKE)
            return user.getShopLiked();
        else
            return user.getShopdisliked();

    }


    /**
     *
     * @param status
     * @return
     */
    @Override
    public Status updateStatus(Status status){
        Status oldStatus = statusRepository.findOne(status.getIdStatus());
        oldStatus.setPassedTime(status.getPassedTime());
        oldStatus.setStatus(status.getStatus());


        return statusRepository.save(oldStatus);
    }

}

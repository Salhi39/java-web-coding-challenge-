package com.hiddenfounders.webcc.service;

import com.hiddenfounders.webcc.model.Shop;
import com.hiddenfounders.webcc.model.Status;
import com.hiddenfounders.webcc.model.User;
import com.hiddenfounders.webcc.model.utility.Constants;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Mohamed SALHI
 * @date Created on 12/26/17 - 12:18 AM
 * @package com.hiddenfounders.webcc.service
 */


public interface MongoDBService {

    User createUser(User user);

    void deleteUser(ObjectId id);

    List<User> findAllUser();

    User findUserById(ObjectId id);

    ResponseEntity updateUserPassword(ObjectId id, String passeword);

    Constants.LOGIN_STATUS  checkPassword(String email, String password);

    void deleteAllUsers();


    Shop createShop(Shop shop);

    void deleteShop(ObjectId id);

    List<Shop> findAllShop();

    Shop findShopById(ObjectId id);

    List<Shop> findAllLikedShop(ObjectId idUser);

    List<Shop> findAllDislikedShop(ObjectId idUser);


    Status createStatus(Status status);

    void deleteStatus(Status status);

    List<Status> findAllStatusWhere(ObjectId idUser, Constants.STATUS status);

    Status updateStatus(Status status);

}

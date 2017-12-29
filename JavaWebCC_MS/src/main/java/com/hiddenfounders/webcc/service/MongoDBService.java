package com.hiddenfounders.webcc.service;

import com.hiddenfounders.webcc.model.Shop;
import com.hiddenfounders.webcc.model.Status;
import com.hiddenfounders.webcc.model.User;
import com.hiddenfounders.webcc.model.utility.Constants;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Mohamed SALHI
 * @date Created on 12/26/17 - 12:18 AM
 * @package com.hiddenfounders.webcc.service
 */


public interface MongoDBService {

    User createUser(User user);

    void deleteUser(Long id);

    List<User> findAllUser();

    User findUserById(Long id);

    ResponseEntity updateUserPassword(Long id, String passeword);




    Shop createShop(Shop user);

    void deleteShop(Long id);

    List<Shop> findAllShop();

    Shop findShopById(Long id);



    Status createStatus(Status status);

    void deleteStatus(Status status);

    List<Status> findAllStatusWhere(Long idUser, Constants.STATUS status);

    Status updateStatus(Status status);

}

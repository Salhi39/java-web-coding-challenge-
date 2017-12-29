package com.hiddenfounders.webcc.service;

import com.hiddenfounders.webcc.model.Shop;
import com.hiddenfounders.webcc.model.Status;
import com.hiddenfounders.webcc.model.User;
import com.hiddenfounders.webcc.model.utility.Constants;
import com.hiddenfounders.webcc.repository.ShopRepository;
import com.hiddenfounders.webcc.repository.StatusRepository;
import com.hiddenfounders.webcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        User persist = new User.UserBuilder()
                    .setEmail(user.getEmail())
                    .setPassword(user.getPassword())
                    .setShopdisliked(user.getShopdisliked())
                    .setShopdisliked(user.getShopdisliked() )
                    .build();
        return userRepository.save(user);
    }


    /**
     *
     * @param id
     */
    @Override
    public void deleteUser(Long id) {
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
    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    public ResponseEntity updateUserPassword(Long id, String passeword) {
        User user = userRepository.findOne(id);
        if(user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        user.setPassword(passeword);
        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
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
                .setIdShop(shop.getIdShop())
                .setName(shop.getName())
                .setEmail(shop.getEmail())
                .setPictureUrl(shop.getPictureUrl())
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
    public void deleteShop(Long id) {
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
    public Shop findShopById(Long id) {
        return shopRepository.findOne(id);
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
                .setShop(status.getShop())
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
    public List<Status> findAllStatusWhere(Long idUser, Constants.STATUS status){
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

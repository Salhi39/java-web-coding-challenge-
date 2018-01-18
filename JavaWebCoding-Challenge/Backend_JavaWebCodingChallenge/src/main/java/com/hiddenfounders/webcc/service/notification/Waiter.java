package com.hiddenfounders.webcc.service.notification;

/**
 * @author Mohamed SALHI
 * @date Created on 1/17/18 - 11:08 PM
 * @package com.hiddenfounders.webcc.service.notification
 */


import com.hiddenfounders.webcc.model.Status;
import com.hiddenfounders.webcc.model.User;
import com.hiddenfounders.webcc.model.utility.Constants;
import com.hiddenfounders.webcc.service.database.MongoDBService;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public class Waiter implements Runnable {

    Status status;
    String userEmail;
    private MongoDBService mongoDBService;

    public Waiter(MongoDBService mongoDBService, Status status, String userEmail) {
        this.mongoDBService = mongoDBService;
        this.status = status;
        this.userEmail = userEmail;
    }

    @Override
    public void run() {
        synchronized (status){
            try {
                System.out.println("Waiter is waiting for the notifier at " +
                        status.getIdShop() +
                        "of user " + userEmail);

                status.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        mongoDBService.removeShopFromList(userEmail, status.getIdShop(), Constants.STATUS.DISLIKE);
        System.out.println("Remove Unlike Tag for " +
                mongoDBService.findShopById(status.getIdShop()).getName());

        
    }

}

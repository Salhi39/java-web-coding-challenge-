package com.hiddenfounders.webcc.service.notification;

/**
 * @author Mohamed SALHI
 * @date Created on 1/17/18 - 11:08 PM
 * @package com.hiddenfounders.webcc.service.notification
 */


import com.hiddenfounders.webcc.model.Status;
import com.hiddenfounders.webcc.model.User;
import com.hiddenfounders.webcc.model.utility.Constants;
import com.hiddenfounders.webcc.repository.ShopRepository;
import com.hiddenfounders.webcc.repository.StatusRepository;
import com.hiddenfounders.webcc.repository.UserRepository;
import com.hiddenfounders.webcc.service.database.MongoDBService;
import com.hiddenfounders.webcc.service.database.MongoDBServiceImp;
import org.bson.types.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public class Notifier implements Runnable {

    private Status status;

    public Notifier(Status status) {
        this.status = status;
    }

    public Notifier(){}


    @Override
    public void run() {
        System.out.println("Notifier is sleeping for 10 seconds at " + new Date());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        synchronized (status) {
            System.out.println("Notifier is notifying waiting thread to wake up at " + new Date());
            status.notify();
        }

    }

}

package com.hiddenfounders.webcc.service.notification;

/**
 * @author Mohamed SALHI
 * @date Created on 1/17/18 - 11:08 PM
 * @package com.hiddenfounders.webcc.service.notification
 */


import com.hiddenfounders.webcc.model.Status;;

import java.util.Date;


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

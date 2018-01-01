package com.hiddenfounders.webcc;

import com.hiddenfounders.webcc.model.Status;
import com.hiddenfounders.webcc.model.User;
import com.hiddenfounders.webcc.model.utility.Constants;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Mohamed SALHI
 * @date Created on 12/26/17 - 12:36 AM
 * @package com.hiddenfounders.webcc
 */


public class Main {


    public static void main( String args[] ) {


        List<Status> shopListLike = new ArrayList<>();
        Status status = new Status.StatusBuilder()
                .setStatus(Constants.STATUS.LIKE)
                .setPassedTime(new Date())
                .setIdShop("5a0c6711fb3aac66aafe26c6")
                .build();
        shopListLike.add(status);

        status = new Status.StatusBuilder()
                .setStatus(Constants.STATUS.LIKE)
                .setPassedTime(new Date())
                .setIdShop("5a0c6711fb3aac66aafe26cc")
                .build();

        shopListLike.add(status);

        System.out.println(shopListLike.toString());
        //System.out.println(Arrays.toString(shopListLike.toArray()));

        /*
        // Creating a Mongo client
        MongoClient mongo = new MongoClient( "localhost" , 27017 );

        // Creating Credentials
        MongoCredential credential;

        credential = MongoCredential.createCredential("mohamed", "shops", "".toCharArray());
        //credential = MongoCredential.createCredential("sampleUser", "myDb",
         //       "password".toCharArray());
        System.out.println("Connected to the database successfully");

        // Accessing the database
        MongoDatabase database = mongo.getDatabase("shops");
        System.out.println("Credentials ::"+ credential);

        MongoCollection<Document> collection = database.getCollection("shops");
        System.out.println("Collection myCollection selected successfully ");

        */

        System.out.println(User.class.getName());
    }
}

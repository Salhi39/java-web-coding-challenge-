package com.hiddenfounders.webcc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.hiddenfounders.webcc.model.utility.Location;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.security.CryptoPrimitive;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


@Document(collection="users")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class User {

    @Id
    private ObjectId idUser;

    private String email;
    private String password;
    private List<Status> shopLiked;
    private List<Status> shopdisliked;

    private Location location;

    public User(String email, String password, List<Status> shopLiked, List<Status> shopdisliked) {
        this.email = email;
        this.password = password;
        this.shopLiked = shopLiked;
        this.shopdisliked = shopdisliked;
    }

    public User(UserBuilder userBuilder) {
        this.idUser = userBuilder.user.getIdUser();
        this.email = userBuilder.user.getEmail();
        this.password = userBuilder.user.getPassword();
        this.shopLiked = userBuilder.user.getShopLiked();
        this.shopdisliked = userBuilder.user.getShopdisliked();
        this.location = userBuilder.user.getLocation();
    }

    public User() {
        this.shopLiked = new ArrayList<>();
        this.shopdisliked = new ArrayList<>();
    }




    public ObjectId getIdUser() {
        return idUser;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = envryptPassword(password);
    }

    public List<Status> getShopLiked() { return shopLiked;}

    public void setShopLiked(List<Status> shopLiked) {this.shopLiked = shopLiked;}

    public List<Status> getShopdisliked() {return shopdisliked;}

    public void setShopDisliked(List<Status> shopdisliked) {this.shopdisliked = shopdisliked;}


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "{" +
                "\"_id_user\": \"" + idUser +"\", " +
                "\" email\": \"" + email +"\", " +
                "\" password\": \"" + password +"\", " +
                "\" shopLiked\": \"" + shopLiked.toString() +"\", " +
                "\" shopdisliked\": \"" + shopdisliked.toString() +"\", " +
                location.toString() +
                "}";
    }

    public void setIdUser(ObjectId idUser) {
        this.idUser = idUser;
    }


    public static class UserBuilder{
        private User user;

        public UserBuilder() {

        }


        public UserBuilder setIdUser(ObjectId idUser) {
            this.user.setIdUser(idUser);
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.user.setEmail(email);
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.setPassword(password);
            return this;
        }


        public UserBuilder setShopLiked(List<Status> shopLiked) {
            this.user.setShopLiked(shopLiked);
            return this;
        }

        public UserBuilder setShopdisliked(List<Status> shopdisliked) {
            this.user.setShopDisliked(shopdisliked);
            return this;
        }


        public UserBuilder setLocation(Location location) {
            this.user.setLocation(location);
            return this;
        }

        public User build(){
            return new User(this);
        }

    }



    public static String envryptPassword(String password){
        String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));

        return generatedSecuredPasswordHash;
    }

}









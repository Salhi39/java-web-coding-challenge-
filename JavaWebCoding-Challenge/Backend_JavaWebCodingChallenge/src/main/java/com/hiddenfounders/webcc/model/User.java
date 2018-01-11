package com.hiddenfounders.webcc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;


@Document(collection="users")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class User {

    @Id
    private ObjectId idUser;
    private String email;
    private String password;
    private List<Status> shopLiked;
    private List<Status> shopdisliked;

    public User(String email, String password, List<Status> shopLiked, List<Status> shopdisliked) {
        this.email = email;
        this.password = password;
        this.shopLiked = shopLiked;
        this.shopdisliked = shopdisliked;
    }

    public User(UserBuilder userBuilder) {
        this.idUser = userBuilder.idUser;
        this.email = userBuilder.email;
        this.password = userBuilder.password;
        this.shopLiked = userBuilder.shopLiked;
        this.shopdisliked = userBuilder.shopdisliked;
    }

    public User() {
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


    @Override
    public String toString() {
        return "{" +
                "\"_id_user\"= \"" + idUser +"\", " +
                "\" email\"=" + email +"\", " +
                "\" password\"=" + password +"\", " +
                "\" shopLiked\"=" + shopLiked.toString() +"\", " +
                "\" shopdisliked\"=" + shopdisliked.toString() +"\"" +
                "}";
    }



    public static class UserBuilder{
        private ObjectId idUser;
        private String email;
        private String password;
        private List<Status> shopLiked;
        private List<Status> shopdisliked;

        public UserBuilder() {

        }


        public UserBuilder setIdUser(ObjectId idUser) {
            this.idUser = idUser;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = User.envryptPassword(password);
            return this;
        }


        public UserBuilder setShopLiked(List<Status> shopLiked) {
            this.shopLiked = shopLiked;
            return this;
        }

        public UserBuilder setShopdisliked(List<Status> shopdisliked) {
            this.shopdisliked = shopdisliked;
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









package com.hiddenfounders.webcc.model;

import java.util.List;

public class User {

    private Long idUser;
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
        this.shopdisliked = userBuilder.shopdisliked;
        this.shopdisliked = userBuilder.shopdisliked;
    }

    public User() {
    }



    public Long getIdUser() {
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
        this.password = password;
    }

    public List<Status> getShopLiked() { return shopLiked;}

    public void setShopLiked(List<Status> shopLiked) {this.shopLiked = shopLiked;}

    public List<Status> getShopdisliked() {return shopdisliked;}

    public void setShopdisliked(List<Status> shopdisliked) {this.shopdisliked = shopdisliked;}


    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", shopLiked=" + shopLiked +
                ", shopdisliked=" + shopdisliked +
                '}';
    }



    public static class UserBuilder{
        private Long idUser;
        private String email;
        private String password;
        private List<Status> shopLiked;
        private List<Status> shopdisliked;

        public UserBuilder() {

        }


        public UserBuilder setIdUser(Long idUser) {
            this.idUser = idUser;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
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
}









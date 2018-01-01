package com.hiddenfounders.webcc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hiddenfounders.webcc.model.utility.Location;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection="shops")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class Shop {


    @Id
    private ObjectId _id;

    private String picture;
    private String name;
    private String email;
    private String city;
    private Location location;


    //**************************************
    //Constructor


    public Shop(String picture, String email, String city,
                String name, Location location) {
        this.picture = picture;
        this.email = email;
        this.city = city;
        this.name = name;
        this.location = location;
    }

    /**
     *
     * @param picture
     * @param name
     */
    public Shop(String picture, String name) {
        this.picture = picture;
        this.name = name;
    }


    /**
     *
     * @param shopBuilder
     */
    public Shop(ShopBuilder shopBuilder){
        this._id = shopBuilder._id;
        this.picture = shopBuilder.picture;
        this.name = shopBuilder.name;
        this.city = shopBuilder.city;
        this.email = shopBuilder.email;
        this.location = shopBuilder.location;
    }


    public Shop(){

    }

    //**************************************
    //Getters


    /**
     *
     * @return
     */
    public ObjectId getIdShop() {
        return _id;
    }


    /**
     *
     * @return
     */
    public String getImage() {
        return picture;
    }

    /**
     *
     * @return
     */
    public String getPicture() {
        return picture;
    }


    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }


    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }


    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }


    /**
     *
     * @return
     */
    public Location getLocation() {
        return location;
    }


    //**************************************
    //Setters

    /**
     *
     * @param picture
     */
    public void setImage(String picture) {
        this.picture = picture;
    }

    /**
     *
     * @param name
     */
    public void setTitle(String name) {
        this.name = name;
    }


    /**
     *
     * @param picture
     * @return
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }


    /**
     *
     * @param email
     * @return
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     *
     * @param city
     * @return
     */
    public void setCity(String city) {
        this.city = city;
    }


    /**
     *
     * @param name
     * @return
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     *
     * @param location
     * @return
     */
    public void setLocation(Location location) {
        this.location = location;
    }


    //**************************************
    //toString

    @Override
    public String toString() {
        return "{" +
                "\"_id\": \"" + _id + "\"" +
                ", \"picture\": \"" + picture + "\"" +
                ", \"name\": \"" + name + "\", " +
                ", \"email\": \"" + email + "\"" +
                ", \"city\": \"" + city + "\", " +
                location.toString()+
                "}";
    }


    //**************************************
    //Builder

    public static class ShopBuilder{
        private ObjectId _id;
        private String picture;
        private String email;
        private String city;
        private String name;
        private Location location;


        public ShopBuilder setIdShop(ObjectId _id) {
            this._id = _id;
            return this;
        }

        public ShopBuilder setPicture(String picture) {
            this.picture = picture;
            return this;
        }

        public ShopBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ShopBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public ShopBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ShopBuilder setLocation(Location location) {
            this.location = location;
            return this;
        }



        public Shop build(){
            return new Shop(this);
        }
    }


}

package com.hiddenfounders.webcc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hiddenfounders.webcc.model.utility.Location;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.List;



@Document(collection="shop")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class Shop {


    @Id
    private Long idShop;

    @NotBlank
    @Size(max=100)
    @Indexed(unique=true)
    private String pictureUrl;
    private String email;
    private String city;
    private String name;
    private Location location;


    //**************************************
    //Constructor


    public Shop(String pictureUrl, String email, String city,
                String name, Location location) {
        this.pictureUrl = pictureUrl;
        this.email = email;
        this.city = city;
        this.name = name;
        this.location = location;
    }

    /**
     *
     * @param pictureUrl
     * @param name
     */
    public Shop(String pictureUrl, String name) {
        this.pictureUrl = pictureUrl;
        this.name = name;
    }


    /**
     *
     * @param shopBuilder
     */
    public Shop(ShopBuilder shopBuilder){
        this.idShop = shopBuilder.idShop;
        this.pictureUrl = shopBuilder.pictureUrl;
        this.name = shopBuilder.name;
    }




    //**************************************
    //Getters


    /**
     *
     * @return
     */
    public Long getIdShop() {
        return idShop;
    }


    /**
     *
     * @return
     */
    public String getImage() {
        return pictureUrl;
    }

    /**
     *
     * @return
     */
    public String getPictureUrl() {
        return pictureUrl;
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
     * @param pictureUrl
     */
    public void setImage(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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
     * @param pictureUrl
     * @return
     */
    public Shop setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }


    /**
     *
     * @param email
     * @return
     */
    public Shop setEmail(String email) {
        this.email = email;
        return this;
    }


    /**
     *
     * @param city
     * @return
     */
    public Shop setCity(String city) {
        this.city = city;
        return this;
    }


    /**
     *
     * @param name
     * @return
     */
    public Shop setName(String name) {
        this.name = name;
        return this;
    }


    /**
     *
     * @param location
     * @return
     */
    public Shop setLocation(Location location) {
        this.location = location;
        return this;
    }


    //**************************************
    //toString

    @Override
    public String toString() {
        return "Shop{" +
                "idShop=" + idShop +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                '}';
    }


    //**************************************
    //Builder

    public static class ShopBuilder{
        private Long idShop;
        private String pictureUrl;
        private String email;
        private String city;
        private String name;
        private Location location;


        public ShopBuilder setIdShop(Long idShop) {
            this.idShop = idShop;
            return this;
        }

        public ShopBuilder setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
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

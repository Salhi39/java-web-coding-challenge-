package com.hiddenfounders.webcc.model;

import com.hiddenfounders.webcc.model.utility.Location;

public class Shop {

    private Long idShop;
    private String pictureUrl;
    private String email;
    private String city;
    private String name;
    private Location location;
    private Status status;



    //**************************************
    //Constructor


    public Shop(String pictureUrl, String email, String city,
                String name, Location location, Status status) {
        this.pictureUrl = pictureUrl;
        this.email = email;
        this.city = city;
        this.name = name;
        this.location = location;
        this.status = status;
    }

    /**
     * @param pictureUrl
     * @param name
     * @param status
     */
    public Shop(String pictureUrl, String name, Status status) {
        this.pictureUrl = pictureUrl;
        this.name = name;
        this.status = status;
    }

    /**
     *
     * @param pictureUrl
     * @param name
     */
    public Shop(String pictureUrl, String name) {
        this.pictureUrl = pictureUrl;
        this.name = name;
        this.status = new Status(idShop);
    }


    /**
     *
     * @param shopBuilder
     */
    public Shop(ShopBuilder shopBuilder){
        this.idShop = shopBuilder.idShop;
        this.pictureUrl = shopBuilder.pictureUrl;
        this.name = shopBuilder.name;
        this.status = shopBuilder.status;
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
    public Status getStatus() {
        return status;
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
     * @return
     */
    public String getTitle() {
        return name;
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
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }





    //**************************************
    //toString


    @Override
    public String toString() {
        return "DAOShop{" +
                "idShop=" + idShop +
                ", pictureUrl=" + pictureUrl +
                ", name='" + name + '\'' +
                ", status=" + status +
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
        private Status status;


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

        public ShopBuilder setStatus(Status status) {
            this.status = status;
            return this;
        }


        public Shop build(){
            return new Shop(this);
        }
    }


}

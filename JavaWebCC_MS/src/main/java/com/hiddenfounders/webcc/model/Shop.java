package com.hiddenfounders.webcc.model;

import java.awt.*;

public class Shop {

    private Long idShop;
    private String imageUrl;
    private String title;

    private Status status;



    //**************************************
    //Constructor

    /**
     * @param imageUrl
     * @param title
     * @param status
     */
    public Shop(String imageUrl, String title, Status status) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.status = status;
    }

    /**
     *
     * @param imageUrl
     * @param title
     */
    public Shop(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.status = new Status(idShop);
    }


    /**
     *
     * @param shopBuilder
     */
    public Shop(ShopBuilder shopBuilder){
        this.idShop = shopBuilder.idShop;
        this.imageUrl = shopBuilder.imageUrl;
        this.title = shopBuilder.title;
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
        return imageUrl;
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
     * @param imageUrl
     */
    public void setImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
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
        return "Shop{" +
                "idShop=" + idShop +
                ", imageUrl=" + imageUrl +
                ", title='" + title + '\'' +
                ", status=" + status +
                '}';
    }



    public static class ShopBuilder{
        private Long idShop;
        private String imageUrl;
        private String title;

        private Status status;



        public ShopBuilder setIdShop(Long idShop) {
            this.idShop = idShop;
            return this;
        }

        public ShopBuilder setImage(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public ShopBuilder setTitle(String title) {
            this.title = title;
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

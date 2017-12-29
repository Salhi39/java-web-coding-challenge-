package com.hiddenfounders.webcc.model;

import com.hiddenfounders.webcc.model.utility.Constants;

import java.util.Date;

public class Status extends Constants {

    private Long idStatus;

    private Shop shop;

    private STATUS status;

    private Date passedTime;


    //**************************************
    //Constructor

    /**
     *
     * @param idUser
     * @param idShop
     * @param status
     * @param passedTime
     */

    public Status(Long idStatus, Shop shop, STATUS status, Date passedTime) {
        this.idStatus = idStatus;
        this.shop = shop;
        this.status = status;
        this.passedTime = passedTime;
    }

    /**
     *
     * @param shop
     * @param status
     * @param passedTime
     */
    public Status(Shop shop, STATUS status, Date passedTime) {
        this.shop = shop;
        this.status = status;
        this.passedTime = passedTime;
    }


    public Status() {
    }

    /**
     *
     * @param statusBuilder
     */
    public Status(StatusBuilder statusBuilder) {
        this.idStatus = statusBuilder.idStatus;
        this.shop = statusBuilder.shop;
        this.status = statusBuilder.status;
        this.passedTime = statusBuilder.passedTime;
    }






    //**************************************
    //Getters

    /**
     *
     * @return idUser
     */
    public Long getIdStatus() {
        return idStatus;
    }


    /**
     *
     * @return
     */
    public Shop getShop() {
        return shop;
    }


    /**
     *
     * @return
     */
    public STATUS getStatus() {
        return status;
    }






    //**************************************
    //Setters


    public void setStatus(STATUS status) {
        this.status = status;
    }


    /**
     *
     * @return
     */
    public Date getPassedTime() {
        return passedTime;
    }


    /**
     *
     * @param passedTime
     */
    public void setPassedTime(Date passedTime) {
        this.passedTime = passedTime;
    }







    //**************************************
    //toString

    /**
     *
     * @return
     */






    //**************************************
    //Builder class


    public static class StatusBuilder{
        private Long idStatus;

        private Shop shop;

        private STATUS status;

        private Date passedTime;


        public StatusBuilder setIdStatus(Long idStatus) {
            this.idStatus = idStatus;
            return this;
        }

        public StatusBuilder setShop(Shop shop) {
            this.shop = shop;
            return this;
        }

        public StatusBuilder setStatus(STATUS status) {
            this.status = status;
            return this;
        }

        public StatusBuilder setPassedTime(Date passedTime) {
            this.passedTime = passedTime;
            return this;
        }

        public Status build(){
            return new Status(this);
        }


    }
}

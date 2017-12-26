package com.hiddenfounders.webcc.model;

import java.util.Date;
import java.util.Timer;

public class Status extends Constants{

    private Long idUser;
    private Long idShop;

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

    public Status(Long idUser, Long idShop, STATUS status, Date passedTime) {
        this.idUser = idUser;
        this.idShop = idShop;
        this.status = status;
        this.passedTime = passedTime;
    }


    /**
     *
     * @param idUser
     * @param idShop
     */
    public Status(Long idUser, Long idShop) {
        this.idUser = idUser;
        this.idShop = idShop;
        this.status = STATUS.NONE;
        this.passedTime = new Date(0);

    }


    /**
     *
     * @param idShop
     */
    public Status(Long idShop) {
        this.idShop = idShop;
        this.status = STATUS.NONE;
        this.passedTime = new Date(0);
    }


    /**
     *
     * @param statusBuilder
     */
    public Status(StatusBuilder statusBuilder) {
        this.idUser = statusBuilder.idUser;
        this.idShop = statusBuilder.idShop;
        this.status = statusBuilder.status;
        this.passedTime = statusBuilder.passedTime;
    }






    //**************************************
    //Getters

    /**
     *
     * @return idUser
     */
    public Long getIdUser() {
        return idUser;
    }


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
    @Override
    public String toString() {
        return "Status{" +
                "idUser=" + idUser +
                ", idShop=" + idShop +
                ", status=" + status +
                ", passedTime=" + passedTime +
                '}';
    }






    //**************************************
    //Builder class


    public static class StatusBuilder{
        private Long idUser;
        private Long idShop;

        private STATUS status;

        private Date passedTime;


        public StatusBuilder setIdUser(Long idUser) {
            this.idUser = idUser;
            return this;
        }

        public StatusBuilder setIdShop(Long idShop) {
            this.idShop = idShop;
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

package com.hiddenfounders.webcc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hiddenfounders.webcc.model.utility.Constants;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection="status")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class Status extends Constants {


    @Id
    private ObjectId idStatus;


    private ObjectId idShop;

    private STATUS status;

    private Date passedTime;


    //**************************************
    //Constructor

    /**
     *
     * @param idStatus
     * @param idShop
     * @param status
     * @param passedTime
     */

    public Status(ObjectId idStatus, ObjectId idShop, STATUS status, Date passedTime) {
        this.idStatus = idStatus;
        this.idShop = idShop;
        this.status = status;
        this.passedTime = passedTime;
    }

    /**
     *
     * @param idShop
     * @param status
     * @param passedTime
     */
    public Status(ObjectId idShop, STATUS status, Date passedTime) {
        this.idShop = idShop;
        this.status = status;
        this.passedTime = passedTime;
    }


    public Status() {
        this.passedTime = new Date();
    }

    /**
     *
     * @param statusBuilder
     */
    public Status(StatusBuilder statusBuilder) {
        this.idStatus = statusBuilder.idStatus;
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
    public ObjectId getIdStatus() {
        return idStatus;
    }


    /**
     *
     * @return
     */
    public ObjectId getIdShop() {
        return idShop;
    }


    /**
     *
     * @return
     */
    public STATUS getStatus() {
        return status;
    }




    /**
     *
     * @return
     */
    public Date getPassedTime() {
        return passedTime;
    }



    //**************************************
    //Setters


    public void setStatus(STATUS status) {
        this.status = status;
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
        return "{" +
                "\"_id_status\": \"" + idStatus + "\"" +
                ", \"id_shop\": \"" + idShop + "\"" +
                ", \"status\": \"" + status + "\"" +
                ", \"passedTime\": \"" + passedTime + "\"" +
                "}";
    }





    //**************************************
    //Builder class


    public static class StatusBuilder{
        private ObjectId idStatus;

        private ObjectId idShop;

        private STATUS status;

        private Date passedTime;


        public StatusBuilder setIdStatus(ObjectId idStatus) {
            this.idStatus = idStatus;
            return this;
        }

        public StatusBuilder setIdShop(ObjectId idShop) {
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

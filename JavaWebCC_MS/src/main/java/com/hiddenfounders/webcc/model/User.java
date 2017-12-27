package com.hiddenfounders.webcc.model;

public class User {

    private Long idUser;
    private String email;
    private String password;


    public User(Long idUser, String email, String password) {
        this.idUser = idUser;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public User(UserBuilder userBuilder) {
        this.idUser = userBuilder.idUser;
        this.email = userBuilder.email;
        this.password = userBuilder.password;
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


    @Override
    public String toString() {
        return "DAOUser{" +
                "idUser=" + idUser +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }





    public static class UserBuilder{
        private Long idUser;
        private String email;
        private String password;


        public UserBuilder(Long idUser, String email, String password) {
            this.idUser = idUser;
            this.email = email;
            this.password = password;
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


        public User build(){
            return new User(this);
        }

    }
}









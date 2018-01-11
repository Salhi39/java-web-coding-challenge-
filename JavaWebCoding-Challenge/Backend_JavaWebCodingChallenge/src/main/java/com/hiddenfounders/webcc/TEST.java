package com.hiddenfounders.webcc;

import com.hiddenfounders.webcc.model.User;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Mohamed SALHI
 * @date Created on 12/28/17 - 8:11 AM
 * @package com.hiddenfounders.webcc
 */


public class TEST {

/*


    public TEST(){
        List<User> userList = new ArrayList<>();
        User user = new User.UserBuilder()
                .setIdUser(""+1)
                .setEmail("a@a.com")
                .setPassword("123456")
                .build();
        userList.add(user);

        user = new User.UserBuilder()
                .setIdUser(""+2)
                .setEmail("b@b.com")
                .setPassword("123456")
                .build();



        userList.add(user);

        user = new User.UserBuilder()
                .setIdUser(""+3)
                .setEmail("c@c.com")
                .setPassword("123456")
                .build();


        userList.add(user);

        user = new User.UserBuilder()
                .setIdUser(""+4)
                .setEmail("e@e.com")
                .setPassword("123456")
                .build();

        userList.add(user);

        System.out.println(userList.toString());
        System.out.println("---------------------");
        List<User> newUser = convertUserToDTOs(userList);

        System.out.println(newUser.toString());
        System.out.println("---------------------");
        for (User u: newUser) {
            System.out.println(u.toString());
        }


    }


*/

    private List<User> convertUserToDTOs(List<User> models) {
        String className = models.getClass().getSimpleName();

        return models.stream()
                .map(this::convertUserToDTO)
                .collect(toList());
    }





    private User convertUserToDTO(User model) {
        User dto = new User.UserBuilder()
                .setIdUser(model.getIdUser())
                .setEmail(model.getEmail())
                .setPassword(model.getPassword())
                .build();

        return dto;
    }


}

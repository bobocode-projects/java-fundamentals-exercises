package com.bobocode;

import com.bobocode.dto.Address;
import com.bobocode.dto.User;
import lombok.SneakyThrows;

public class DemoApp {
    @SneakyThrows
    public static void main(String[] args) {
        var user =  new User(
                123L,
                "Jackson",
                "Mapper",
                new Address("Kyiv", "Shevchenka", "2A")
        );
        
        Class<User> userClass = User.class;
        for (var field :userClass.getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println(field.getName() + " - "+field.getType() + " - "+ field.get(user));
        } 
    }
}

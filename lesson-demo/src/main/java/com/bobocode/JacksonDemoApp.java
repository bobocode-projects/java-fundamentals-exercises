package com.bobocode;

import com.bobocode.dto.Address;
import com.bobocode.dto.User;

public class JacksonDemoApp {
    public static void main(String[] args) {
        var user = loadUserFromDb(123L);
        var userJson = covertObjectToJson(user);
        sendUserJsonToClient(userJson);
    }

    private static User loadUserFromDb(long id) {
        return new User(
                id,
                "Jackson",
                "Mapper",
                new Address("Kyiv", "Shevchenka", "2A")
        );
    }

    // todo: unify object to JSON conversion logic
    private static String covertObjectToJson(Object obj) {
        throw new UnsupportedOperationException(); // todo:
    }

    private static void sendUserJsonToClient(String userJson) {
        System.out.println("Sending JSON to the client:");
        System.out.println(userJson);
    }
}

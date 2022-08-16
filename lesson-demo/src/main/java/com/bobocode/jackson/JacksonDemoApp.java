package com.bobocode.jackson;

import com.bobocode.jackson.dto.Address;
import com.bobocode.jackson.dto.User;
import lombok.SneakyThrows;

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
        var stringBuilder = new StringBuilder();
        convertToJsonHelper(stringBuilder, obj, 1);
        return stringBuilder.toString();
    }

    @SneakyThrows
    private static void convertToJsonHelper(StringBuilder stringBuilder, Object obj, int tabs) {
        stringBuilder.append("{\n");
        var fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            var field = fields[i];
            field.setAccessible(true);
            var name = field.getName();
            var value = field.get(obj);
            stringBuilder.append("\t".repeat(tabs));
            stringBuilder.append("\"").append(name).append("\": ");
            if (value instanceof Number) {
                stringBuilder.append(value);
            } else if (value instanceof String) {
                stringBuilder.append("\"").append(value).append("\"");
            } else {
                convertToJsonHelper(stringBuilder, value, tabs + 1);
            }
            if (i < fields.length - 1) {
                stringBuilder.append(",");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("\t".repeat(tabs - 1)).append("}");
    }

    private static void sendUserJsonToClient(String userJson) {
        System.out.println("Sending JSON to the client:");
        System.out.println(userJson);
    }
}

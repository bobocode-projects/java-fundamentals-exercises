package com.bobocode.spring;

import lombok.SneakyThrows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class SpringGenericReflectionDemoApp {
    @SneakyThrows
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext("com.bobocode.spring");
        ConferenceService conferenceService = context.getBean(ConferenceService.class);
        conferenceService.run();

        var talkingServiceField = conferenceService.getClass().getDeclaredFields()[0];
        var parameterizedType = (ParameterizedType) talkingServiceField.getGenericType();
        var typeArgument = parameterizedType.getActualTypeArguments()[0];
        var className = typeArgument.getTypeName();
        
        Class<?> typeToken = Class.forName(className);
        var talkingServices = context.getBeansOfType(typeToken).values();
        System.out.println(talkingServices);
        
        
    }

    private <T> T[] createNewArray(int size) {
//        T newElement = new T();
//        return new T[size];
        return null;
    }
}

package com.bobocode;

import com.bobocode.service.ConferenceService;
import com.bobocode.service.TalkingService;
import lombok.SneakyThrows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class SpringDemoApp {
    @SneakyThrows
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext("com.bobocode.service");
        var conferenceService = context.getBean(ConferenceService.class);
        // how spring works
        var field = conferenceService.getClass().getDeclaredField("talkingServiceList");
        ParameterizedType genericType = (ParameterizedType) field.getGenericType();
        Type actualTypeArgument = genericType.getActualTypeArguments()[0];
        Class<?> talkingServiceClass = Class.forName(actualTypeArgument.getTypeName());
        System.out.println(talkingServiceClass);
        Map<String, ?> beanMaps = context.getBeansOfType(talkingServiceClass);
        beanMaps.values().stream()
                .map(bean -> (TalkingService)bean)
                .forEach(TalkingService::talk);


    }

}

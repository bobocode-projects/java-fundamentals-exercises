package com.bobocode.basics;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoxTest {
    private final String TYPE_PARAMETER_NAME = "T";

    @Test
    @Order(1)
    @DisplayName("Box class has one type parameter")
    void boxClassHasOneTypeParameter() {
        var typeParameters = Box.class.getTypeParameters();

        assertThat(typeParameters.length).isEqualTo(1);
    }

    @Test
    @Order(2)
    @DisplayName("Type parameter is called \"T\"")
    void typeParameterIsCalledT() {
        var typeParameter = Box.class.getTypeParameters()[0];

        assertThat(typeParameter.getName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @Order(3)
    @DisplayName("Type parameter \"T\" is not bounded")
    void typeParameterIsNotBounded() {
        var typeParameter = Box.class.getTypeParameters()[0];

        assertThat(typeParameter.getBounds()).hasSize(1);
        assertThat(typeParameter.getBounds()[0].getTypeName()).isEqualTo(Object.class.getTypeName());
    }

    @Test
    @SneakyThrows
    @Order(4)
    @DisplayName("Field \"value\" is \"T\"")
    void valueFieldIsGeneric() {
        var valueField = Box.class.getDeclaredField("value");
        var genericType = valueField.getGenericType();

        assertThat(genericType.getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @SneakyThrows
    @Order(5)
    @DisplayName("Constructor parameter type is \"T\"")
    void constructorParameterIsGeneric() {
        var constructor = Box.class.getDeclaredConstructors()[0];
        assert (constructor.getParameters().length == 1);
        var parameter = constructor.getParameters()[0];

        assertThat(parameter.getParameterizedType().getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @SneakyThrows
    @Order(6)
    @DisplayName("Getter return type is \"T\"")
    void getterReturnTypeIsGeneric() {
        var getter = Box.class.getDeclaredMethod("getValue");

        assertThat(getter.getGenericReturnType().getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @SneakyThrows
    @Order(7)
    @DisplayName("Setter parameter type is \"T\"")
    void setterParameterIsGeneric() {
        var setter = Box.class.getDeclaredMethod("setValue", Object.class);
        assert (setter.getParameters().length == 1);
        var parameter = setter.getParameters()[0];

        assertThat(parameter.getParameterizedType().getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }
}

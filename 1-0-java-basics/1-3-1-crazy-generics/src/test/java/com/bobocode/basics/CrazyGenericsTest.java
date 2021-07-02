package com.bobocode.basics;

import com.bobocode.basics.CrazyGenerics.*;
import com.bobocode.basics.util.BaseEntity;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrazyGenericsTest {
    final String TYPE_PARAMETER_NAME = "T";
    final String SECOND_TYPE_PARAMETER_NAME = "R";

    @Test
    @Order(1)
    @DisplayName("Sourced class has one type parameter")
    void sourcedClassHasOneTypeParameter() {
        var typeParameters = Sourced.class.getTypeParameters();

        assertThat(typeParameters.length).isEqualTo(1);
    }

    @Test
    @Order(2)
    @DisplayName("Sourced class type parameter is called \"T\"")
    void sourcedClassTypeParameterIsCalledT() {
        var typeParameter = Sourced.class.getTypeParameters()[0];

        assertThat(typeParameter.getName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @Order(3)
    @DisplayName("Sourced class type parameter \"T\" is not bounded")
    void sourcedClassTypeParameterIsNotBounded() {
        var typeParameter = Sourced.class.getTypeParameters()[0];

        assertThat(typeParameter.getBounds()).hasSize(1);
        assertThat(typeParameter.getBounds()[0].getTypeName()).isEqualTo(Object.class.getTypeName());
    }

    @Test
    @SneakyThrows
    @Order(4)
    @DisplayName("Sourced class field \"value\" has generic type \"T\"")
    void valueFieldIsGeneric() {
        var valueField = Sourced.class.getDeclaredField("value");
        var genericType = valueField.getGenericType();

        assertThat(genericType.getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }


    @Test
    @Order(1)
    @DisplayName("Bounded class has one type parameter")
    void boundedClassHasOneTypeParameter() {
        var typeParameters = Bounded.class.getTypeParameters();

        assertThat(typeParameters.length).isEqualTo(1);
    }

    @Test
    @Order(1)
    @DisplayName("Bounded class type parameter is bounded by Number")
    void boundedClassTypeParameterIsBoundedByNumber() {
        var typeParameters = Bounded.class.getTypeParameters();
        assert (typeParameters.length == 1);
        var typeParam = typeParameters[0];
        assert (typeParam.getBounds().length == 1);
        var boundType = typeParam.getBounds()[0];

        assertThat(boundType.getTypeName()).isEqualTo(Number.class.getTypeName());
    }

    @Test
    @SneakyThrows
    @Order(4)
    @DisplayName("Bounded class fields have generic type \"T\"")
    void boundedClassFieldsAreGeneric() {
        var fields = Bounded.class.getDeclaredFields();

        for (var f : fields) {
            assertThat(f.getGenericType().getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
        }
    }

    @Test
    @DisplayName("Converter interface has two type parameters")
    void converterInterfaceHasTwoTypeParameters() {
        var typeParameters = Converter.class.getTypeParameters();

        assertThat(typeParameters).hasSize(2);
    }

    @Test
    @DisplayName("Converter interface first type parameter is called \"T\"")
    void converterInterfaceFirstTypeParameterIsCalledT() {
        var typeParameters = Converter.class.getTypeParameters();
        assert (typeParameters.length == 2);
        var firstTypeParam = typeParameters[0];

        assertThat(firstTypeParam.getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @DisplayName("Converter interface second type parameter is called \"R\"")
    void converterInterfaceSecondTypeParameterIsCalledR() {
        var typeParameters = Converter.class.getTypeParameters();
        assert (typeParameters.length == 2);
        var firstTypeParam = typeParameters[1];

        assertThat(firstTypeParam.getTypeName()).isEqualTo(SECOND_TYPE_PARAMETER_NAME);
    }

    @Test
    @SneakyThrows
    @DisplayName("Convert method parameter \"obj\" has type \"T\"")
    void converterMethodParameterHasTypeT() {
        var convertMethod = Converter.class.getDeclaredMethod("convert", Object.class);
        var objParam = convertMethod.getParameters()[0];

        assertThat(objParam.getParameterizedType().getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @SneakyThrows
    @DisplayName("Convert method return type is \"R\"")
    void converterMethodReturnTypeIsR() {
        var convertMethod = Converter.class.getDeclaredMethod("convert", Object.class);

        assertThat(convertMethod.getGenericReturnType().getTypeName()).isEqualTo(SECOND_TYPE_PARAMETER_NAME);
    }

    @Test
    @DisplayName("MaxHolder class has one type parameter")
    void maxHolderClassHasOneTypeParameter() {
        var typeParameters = MaxHolder.class.getTypeParameters();

        assertThat(typeParameters).hasSize(1);
    }

    @Test
    @DisplayName("MaxHolder class type parameter is called \"T\"")
    void maxHolderClassTypeParameterIsCalledT() {
        var typeParameter = MaxHolder.class.getTypeParameters()[0];

        assertThat(typeParameter.getName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @DisplayName("MaxHolder type parameter is bound by Comparable")
    void maxHolderClassTypeParameterShouldBeBoundByComparableT() {
        var typeParameters = MaxHolder.class.getTypeParameters();
        assert (typeParameters.length == 1);
        var typeParam = typeParameters[0];
        assert (typeParam.getBounds().length == 1);
        var boundType = typeParam.getBounds()[0];

        var expectedBoundTypeName = String.format("%s<%s>", Comparable.class.getTypeName(), TYPE_PARAMETER_NAME);
        assertThat(boundType.getTypeName()).isEqualTo(expectedBoundTypeName);
    }

    @Test
    @DisplayName("MaxHolder field has type \"T\"")
    void maxHolderFieldHasTypeT() {
        var typeParameter = MaxHolder.class.getTypeParameters()[0];

        assertThat(typeParameter.getName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @SneakyThrows
    @DisplayName("MaxHolder constructor param has type \"T\"")
    void maxHolderConstructorParamHasTypeT() {
        var constructor = MaxHolder.class.getConstructors()[0];
        assert (constructor.getParameters().length == 1);
        var param = constructor.getParameters()[0];

        assertThat(param.getParameterizedType().getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @SneakyThrows
    @DisplayName("MaxHolder put method param has type \"T\"")
    void maxHolderPutMethodParamHasTypeT() {
        var putMethod = Arrays.stream(MaxHolder.class.getMethods())
                .filter(method -> method.getName().equals("put"))
                .findAny()
                .orElseThrow();
        assert (putMethod.getParameters().length == 1);
        var param = putMethod.getParameters()[0];

        assertThat(param.getParameterizedType().getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @SneakyThrows
    @DisplayName("MaxHolder getMax return type is \"T\"")
    void maxHolderGetMaxReturnTypeIsT() {
        var getMaxMethod = MaxHolder.class.getDeclaredMethod("getMax");

        assertThat(getMaxMethod.getGenericReturnType().getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @DisplayName("StrictProcessor class has one generic type")
    void strictProcessorHasOneGenericType() {
        var typeParameters = StrictProcessor.class.getTypeParameters();

        assertThat(typeParameters).hasSize(1);
    }

    @Test
    @DisplayName("StrictProcessor type parameter is called \"T\"")
    void strictProcessorTypeParameterIsCalledT() {
        var typeParameters = StrictProcessor.class.getTypeParameters();
        assert (typeParameters.length == 1);
        var typePram = typeParameters[0];

        assertThat(typePram.getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @DisplayName("StrictProcessor type parameter is bound by both Serializable and Comparable<T>")
    void strictProcessorTypeParameterIsBoundBySerializableAndComparable() {
        var typeParameters = StrictProcessor.class.getTypeParameters();
        assert (typeParameters.length == 1);
        var typeParam = typeParameters[0];
        assert (typeParam.getBounds().length == 2);
        var serializableBoundType = typeParam.getBounds()[0];
        var comparableBoundType = typeParam.getBounds()[1];

        assertThat(serializableBoundType.getTypeName())
                .isEqualTo(Serializable.class.getTypeName());
        assertThat(comparableBoundType.getTypeName())
                .isEqualTo(String.format("%s<%s>", Comparable.class.getTypeName(), TYPE_PARAMETER_NAME));
    }

    @Test
    @DisplayName("StrictProcessor process method parameter has type \"T\"")
    void strictProcessorProcessMethodParameterHasTypeT() {
        var processMethod = Arrays.stream(StrictProcessor.class.getMethods())
                .filter(method -> method.getName().equals("process"))
                .findAny()
                .orElseThrow();
        assert (processMethod.getParameters().length == 1);
        var processMethodParam = processMethod.getParameters()[0];

        assertThat(processMethodParam.getParameterizedType().getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @DisplayName("CollectionRepository has two type parameters")
    void collectionRepositoryHasTwoTypeParameters() {
        var typeParameters = CollectionRepository.class.getTypeParameters();

        assertThat(typeParameters).hasSize(2);
    }

    @Test
    @DisplayName("CollectionRepository first type parameter is called \"T\"")
    void collectionRepositoryFirstTypeParameterIsCalledT() {
        var typeParam = CollectionRepository.class.getTypeParameters()[0];

        assertThat(typeParam.getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @DisplayName("CollectionRepository first type parameter is bounded by BaseEntity")
    void collectionRepositoryFirstTypeParameterIsBoundedByBaseEntity() {
        var typeParam = CollectionRepository.class.getTypeParameters()[0];
        var boundType = typeParam.getBounds()[0];

        assertThat(boundType.getTypeName()).isEqualTo(BaseEntity.class.getTypeName());
    }

    @Test
    @DisplayName("CollectionRepository second type parameter is called \"C\"")
    void collectionRepositorySecondTypeParameterIsCalledT() {
        var typeParam = CollectionRepository.class.getTypeParameters()[0];

        assertThat(typeParam.getTypeName()).isEqualTo("C");
    }

    @Test
    @DisplayName("CollectionRepository second type parameter is bounded by Collection<T>")
    void collectionRepositorySecondTypeParameterIsBoundedByCollection() {
        var typeParam = CollectionRepository.class.getTypeParameters()[1];
        var boundType = typeParam.getBounds()[0];

        assertThat(boundType.getTypeName())
                .isEqualTo(String.format("%s<T>", Collection.class.getTypeName()));
    }

    @Test
    @DisplayName("CollectionRepository save method param has type \"T\"")
    @SneakyThrows
    void collectionRepositorySaveMethodParameterHasTypeT() {
        var saveMethod = Arrays.stream(CollectionRepository.class.getMethods())
                .filter(method -> method.getName().equals("save"))
                .findAny()
                .orElseThrow();
        var methodParam = saveMethod.getParameters()[0];

        assertThat(methodParam.getParameterizedType().getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @DisplayName("CollectionRepository getEntityCollection method has return type \"C\"")
    @SneakyThrows
    void collectionRepositoryGetCollectionMethodHasReturnTypeC() {
        var getEntityCollectionMethod = CollectionRepository.class.getMethod("getEntityCollection");

        assertThat(getEntityCollectionMethod.getGenericReturnType().getTypeName()).isEqualTo("C");
    }

    @Test
    @DisplayName("ListRepository extends CollectionRepository")
    void listRepositoryExtendsCollectionRepository() {
        var superInterface = ListRepository.class.getInterfaces()[0];

        assertThat(superInterface.getTypeName()).isEqualTo(CollectionRepository.class.getTypeName());
    }

    @Test
    @DisplayName("ListRepository has one type parameter")
    void listRepositoryHasOneTypeParameter() {
        var typeParams = ListRepository.class.getTypeParameters();

        assertThat(typeParams).hasSize(1);
    }

    @Test
    @DisplayName("ListRepository type parameter is called \"T\"")
    void listRepositoryTypeParameterIsCalledT() {
        var typeParam = ListRepository.class.getTypeParameters()[0];

        assertThat(typeParam.getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @DisplayName("ListRepository type parameter is bound by BaseEntity")
    void listRepositoryTypeParameterIsBoundByBaseEntity() {
        var typeParam = ListRepository.class.getTypeParameters()[0];
        var boundType = typeParam.getBounds()[0];

        assertThat(boundType.getTypeName()).isEqualTo(BaseEntity.class.getTypeName());
    }

    @Test
    @DisplayName("ListRepository parent interface has specified two types")
    void listRepositoryParentInterfaceHasSpecifiedTwoTypes() {
        var collectionInterface = ListRepository.class.getGenericInterfaces()[0];

        assertThat(collectionInterface.getTypeName())
                .isEqualTo(String.format("%s<T, %s<T>>", CollectionRepository.class.getTypeName(), List.class.getTypeName()));
    }
}

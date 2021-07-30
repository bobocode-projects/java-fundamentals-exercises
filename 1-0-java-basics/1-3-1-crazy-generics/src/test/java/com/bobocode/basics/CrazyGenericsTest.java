package com.bobocode.basics;

import com.bobocode.basics.CrazyGenerics.*;
import com.bobocode.basics.util.BaseEntity;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrazyGenericsTest {
    final String TYPE_PARAMETER_NAME = "T";
    final String SECOND_TYPE_PARAMETER_NAME = "R";
    final String COLLECTION_ELEMENT_TYPE_PARAMETER_NAME = "E";

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
    @Order(5)
    @DisplayName("Limited class has one type parameter")
    void limitedClassHasOneTypeParameter() {
        var typeParameters = Limited.class.getTypeParameters();

        assertThat(typeParameters.length).isEqualTo(1);
    }

    @Test
    @Order(6)
    @DisplayName("Limited class type parameter is bounded by Number")
    void limitedClassTypeParameterIsBoundedByNumber() {
        var typeParameters = Limited.class.getTypeParameters();
        assert (typeParameters.length == 1);
        var typeParam = typeParameters[0];
        assert (typeParam.getBounds().length == 1);
        var boundType = typeParam.getBounds()[0];

        assertThat(boundType.getTypeName()).isEqualTo(Number.class.getTypeName());
    }

    @Test
    @SneakyThrows
    @Order(7)
    @DisplayName("Limited class fields have generic type \"T\"")
    void limitedClassFieldsAreGeneric() {
        var fields = Limited.class.getDeclaredFields();

        for (var f : fields) {
            assertThat(f.getGenericType().getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
        }
    }

    @Test
    @Order(8)
    @DisplayName("Converter interface has two type parameters")
    void converterInterfaceHasTwoTypeParameters() {
        var typeParameters = Converter.class.getTypeParameters();

        assertThat(typeParameters).hasSize(2);
    }

    @Test
    @Order(9)
    @DisplayName("Converter interface first type parameter is called \"T\"")
    void converterInterfaceFirstTypeParameterIsCalledT() {
        var typeParameters = Converter.class.getTypeParameters();
        assert (typeParameters.length == 2);
        var firstTypeParam = typeParameters[0];

        assertThat(firstTypeParam.getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @Order(10)
    @DisplayName("Converter interface second type parameter is called \"R\"")
    void converterInterfaceSecondTypeParameterIsCalledR() {
        var typeParameters = Converter.class.getTypeParameters();
        assert (typeParameters.length == 2);
        var firstTypeParam = typeParameters[1];

        assertThat(firstTypeParam.getTypeName()).isEqualTo(SECOND_TYPE_PARAMETER_NAME);
    }

    @Test
    @SneakyThrows
    @Order(11)
    @DisplayName("Convert method parameter \"obj\" has type \"T\"")
    void converterMethodParameterHasTypeT() {
        var convertMethod = Converter.class.getDeclaredMethod("convert", Object.class);
        var objParam = convertMethod.getParameters()[0];

        assertThat(objParam.getParameterizedType().getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @SneakyThrows
    @Order(12)
    @DisplayName("Convert method return type is \"R\"")
    void converterMethodReturnTypeIsR() {
        var convertMethod = Converter.class.getDeclaredMethod("convert", Object.class);

        assertThat(convertMethod.getGenericReturnType().getTypeName()).isEqualTo(SECOND_TYPE_PARAMETER_NAME);
    }

    @Test
    @Order(13)
    @DisplayName("MaxHolder class has one type parameter")
    void maxHolderClassHasOneTypeParameter() {
        var typeParameters = MaxHolder.class.getTypeParameters();

        assertThat(typeParameters).hasSize(1);
    }

    @Test
    @Order(14)
    @DisplayName("MaxHolder class type parameter is called \"T\"")
    void maxHolderClassTypeParameterIsCalledT() {
        var typeParameter = MaxHolder.class.getTypeParameters()[0];

        assertThat(typeParameter.getName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @Order(15)
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
    @Order(15)
    @DisplayName("MaxHolder field has type \"T\"")
    void maxHolderFieldHasTypeT() {
        var typeParameter = MaxHolder.class.getTypeParameters()[0];

        assertThat(typeParameter.getName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @Order(16)
    @SneakyThrows
    @DisplayName("MaxHolder constructor param has type \"T\"")
    void maxHolderConstructorParamHasTypeT() {
        var constructor = MaxHolder.class.getConstructors()[0];
        assert (constructor.getParameters().length == 1);
        var param = constructor.getParameters()[0];

        assertThat(param.getParameterizedType().getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @Order(17)
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
    @Order(18)
    @SneakyThrows
    @DisplayName("MaxHolder getMax return type is \"T\"")
    void maxHolderGetMaxReturnTypeIsT() {
        var getMaxMethod = MaxHolder.class.getDeclaredMethod("getMax");

        assertThat(getMaxMethod.getGenericReturnType().getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @Order(19)
    @DisplayName("StrictProcessor class has one generic type")
    void strictProcessorHasOneGenericType() {
        var typeParameters = StrictProcessor.class.getTypeParameters();

        assertThat(typeParameters).hasSize(1);
    }

    @Test
    @Order(20)
    @DisplayName("StrictProcessor type parameter is called \"T\"")
    void strictProcessorTypeParameterIsCalledT() {
        var typeParameters = StrictProcessor.class.getTypeParameters();
        assert (typeParameters.length == 1);
        var typePram = typeParameters[0];

        assertThat(typePram.getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @Order(21)
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
    @Order(22)
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
    @Order(23)
    @DisplayName("CollectionRepository has two type parameters")
    void collectionRepositoryHasTwoTypeParameters() {
        var typeParameters = CollectionRepository.class.getTypeParameters();

        assertThat(typeParameters).hasSize(2);
    }

    @Test
    @Order(24)
    @DisplayName("CollectionRepository first type parameter is called \"T\"")
    void collectionRepositoryFirstTypeParameterIsCalledT() {
        var typeParam = CollectionRepository.class.getTypeParameters()[0];

        assertThat(typeParam.getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @Order(25)
    @DisplayName("CollectionRepository first type parameter is bounded by BaseEntity")
    void collectionRepositoryFirstTypeParameterIsBoundedByBaseEntity() {
        var typeParam = CollectionRepository.class.getTypeParameters()[0];
        var boundType = typeParam.getBounds()[0];

        assertThat(boundType.getTypeName()).isEqualTo(BaseEntity.class.getTypeName());
    }

    @Test
    @Order(26)
    @DisplayName("CollectionRepository second type parameter is called \"C\"")
    void collectionRepositorySecondTypeParameterIsCalledT() {
        var typeParam = CollectionRepository.class.getTypeParameters()[1];

        assertThat(typeParam.getTypeName()).isEqualTo("C");
    }

    @Test
    @Order(27)
    @DisplayName("CollectionRepository second type parameter is bounded by Collection<T>")
    void collectionRepositorySecondTypeParameterIsBoundedByCollection() {
        var typeParam = CollectionRepository.class.getTypeParameters()[1];
        var boundType = typeParam.getBounds()[0];

        assertThat(boundType.getTypeName())
                .isEqualTo(String.format("%s<T>", Collection.class.getTypeName()));
    }

    @Test
    @Order(28)
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
    @Order(29)
    @DisplayName("CollectionRepository getEntityCollection method has return type \"C\"")
    @SneakyThrows
    void collectionRepositoryGetCollectionMethodHasReturnTypeC() {
        var getEntityCollectionMethod = CollectionRepository.class.getMethod("getEntityCollection");

        assertThat(getEntityCollectionMethod.getGenericReturnType().getTypeName()).isEqualTo("C");
    }

    @Test
    @Order(30)
    @DisplayName("ListRepository extends CollectionRepository")
    void listRepositoryExtendsCollectionRepository() {
        var superInterface = ListRepository.class.getInterfaces()[0];

        assertThat(superInterface.getTypeName()).isEqualTo(CollectionRepository.class.getTypeName());
    }

    @Test
    @Order(31)
    @DisplayName("ListRepository has one type parameter")
    void listRepositoryHasOneTypeParameter() {
        var typeParams = ListRepository.class.getTypeParameters();

        assertThat(typeParams).hasSize(1);
    }

    @Test
    @Order(32)
    @DisplayName("ListRepository type parameter is called \"T\"")
    void listRepositoryTypeParameterIsCalledT() {
        var typeParam = ListRepository.class.getTypeParameters()[0];

        assertThat(typeParam.getTypeName()).isEqualTo(TYPE_PARAMETER_NAME);
    }

    @Test
    @Order(33)
    @DisplayName("ListRepository type parameter is bound by BaseEntity")
    void listRepositoryTypeParameterIsBoundByBaseEntity() {
        var typeParam = ListRepository.class.getTypeParameters()[0];
        var boundType = typeParam.getBounds()[0];

        assertThat(boundType.getTypeName()).isEqualTo(BaseEntity.class.getTypeName());
    }

    @Test
    @Order(34)
    @DisplayName("ListRepository parent interface has specified two types")
    void listRepositoryParentInterfaceHasSpecifiedTwoTypes() {
        var collectionInterface = ListRepository.class.getGenericInterfaces()[0];

        assertThat(collectionInterface.getTypeName())
                .isEqualTo(String.format("%s<T, %s<T>>", CollectionRepository.class.getTypeName(), List.class.getTypeName()));
    }

    @Test
    @Order(35)
    @DisplayName("ConsoleUtil is not a generic class")
    void consoleUtilIsNotAGenericClass() {
        var typeParams = ConsoleUtil.class.getTypeParameters();

        assertThat(typeParams).isEmpty();
    }

    @Test
    @Order(36)
    @DisplayName("Method print param is a list of any type declared as unbounded wildcard")
    void printParamIsAListOfAnyType() {
        var printMethod = Arrays.stream(ConsoleUtil.class.getDeclaredMethods())
                .filter(method -> method.getName().equals("print"))
                .findAny().orElseThrow();
        var listParam = printMethod.getParameters()[0];
        var typeName = listParam.getParameterizedType().getTypeName();

        assertThat(typeName).isEqualTo(String.format("%s<?>", List.class.getTypeName()));

    }

    @Test
    @Order(37)
    @DisplayName("ComparableCollection has one type parameter \"E\"")
    void comparableCollectionIsGeneric() {
        var typeParameters = ComparableCollection.class.getTypeParameters();

        assertThat(typeParameters.length).isEqualTo(1);
        assertThat(typeParameters[0].getName()).isEqualTo(COLLECTION_ELEMENT_TYPE_PARAMETER_NAME);
    }

    @Test
    @Order(38)
    @DisplayName("ComparableCollection extends Collection")
    void comparableCollectionExtendsCollection() {
        var collectionInterface = Arrays.stream(ComparableCollection.class.getInterfaces())
                .filter(it -> it.getTypeName().equals(Collection.class.getTypeName()))
                .findAny()
                .orElseThrow();

        assertThat(collectionInterface).isNotNull();
    }

    @Test
    @Order(39)
    @DisplayName("Type parameter is specified for a super interface Collection")
    void comparableCollectionExtendsCollectionOfTheSameElementsType() {
        var collectionInterface = Arrays.stream(ComparableCollection.class.getGenericInterfaces())
                .filter(it -> it.getTypeName().equals(
                        String.format("%s<%s>", Collection.class.getTypeName(), COLLECTION_ELEMENT_TYPE_PARAMETER_NAME)
                ))
                .findAny()
                .orElseThrow();

        assertThat(collectionInterface).isNotNull();
    }

    @Test
    @Order(40)
    @DisplayName("ComparableCollection extends Comparable")
    void comparableCollectionExtendsComparable() {
        var comparableInterface = Arrays.stream(ComparableCollection.class.getInterfaces())
                .filter(it -> it.getTypeName().equals(Comparable.class.getTypeName()))
                .findAny()
                .orElseThrow();

        assertThat(comparableInterface).isNotNull();
    }

    @Test
    @Order(41)
    @DisplayName("Collection of any type is specified as type parameter for a super interface Comparable")
    void comparableCollectionExtendsComparableOfCollectionOfAnyType() {
        var comparableInterface = Arrays.stream(ComparableCollection.class.getGenericInterfaces())
                .filter(it -> it.getTypeName().equals(
                        String.format("%s<%s<?>>", Comparable.class.getTypeName(), Collection.class.getTypeName())
                ))
                .findAny()
                .orElseThrow();

        assertThat(comparableInterface).isNotNull();
    }

    @Test
    @Order(42)
    @DisplayName("Method compareTo is overridden")
    void comparableCollectionOverridesCompareToMethod() {
        var compareToMethod = Arrays.stream(ComparableCollection.class.getDeclaredMethods())
                .filter(method -> method.getName().equals("compareTo"))
                .findAny()
                .orElseThrow();

        assertThat(compareToMethod).isNotNull();
    }

    @Test
    @Order(43)
    @DisplayName("ComparableCollection provides a default impl of compareTo method")
    void compareToProvidesDefaultImpl() {
        var compareToMethod = Arrays.stream(ComparableCollection.class.getDeclaredMethods())
                .filter(method -> method.getName().equals("compareTo"))
                .findAny()
                .orElseThrow();

        assertThat(compareToMethod.isDefault()).isTrue();
    }

    @Test
    @Order(44)
    @DisplayName("A parameter of method compareTo is a collection of elements of any type")
    void compareToParamIsACollectionOfAnyType() {
        var compareToMethod = Arrays.stream(ComparableCollection.class.getDeclaredMethods())
                .filter(method -> method.getName().equals("compareTo"))
                .findAny()
                .orElseThrow();
        var collectionParam = compareToMethod.getParameters()[0];

        assertThat(collectionParam.getParameterizedType().getTypeName())
                .isEqualTo(String.format("%s<?>", Collection.class.getTypeName()));
    }


    @Order(45)
    @DisplayName("Method compareTo compares collection size")
    @SneakyThrows
    @ParameterizedTest
    @ValueSource(ints = {0, 5, 10})
    void compareToComparesSize(int size) {
        var compareToMethod = Arrays.stream(ComparableCollection.class.getDeclaredMethods())
                .filter(method -> method.getName().equals("compareTo"))
                .findAny().orElseThrow();
        var compCollectionMock = Mockito.spy(ComparableCollection.class);
        var sizeMethod = Arrays.stream(ComparableCollection.class.getMethods())
                .filter(method -> method.getName().equals("size"))
                .findAny().orElseThrow();
        when(sizeMethod.invoke(compCollectionMock)).thenReturn(size);
        var list = List.of(1, 2, 3, 4, 5);

        assertThat(compareToMethod.invoke(compCollectionMock, list))
                .isEqualTo(Integer.compare(size, list.size()));
    }

    @Test
    @DisplayName("Method hasNewEntities accepts a collection of any entities (BaseEntity subclasses)")
    void hasNewEntitiesMethodParamIsAGenericCollectionOfEntities() {
        var hasNewEntitiesMethod = getMethodByName(PersistenceUtil.class, "hasNewEntities");

        var entitiesParam = hasNewEntitiesMethod.getParameters()[0];

        assertThat(entitiesParam.getParameterizedType().getTypeName())
                .isEqualTo(String.format("%s<? extends %s>", Collection.class.getTypeName(), BaseEntity.class.getTypeName()));
    }

    @ParameterizedTest
    @MethodSource("hasNewEntitiesArgs")
    @DisplayName("Method hasNewEntities checks id values")
    @SneakyThrows
    void hasNewEntitiesChecksIds(Collection<BaseEntity> entities, boolean result) {
        var hasNewEntitiesMethod = getMethodByName(PersistenceUtil.class, "hasNewEntities");

        boolean hasNewEntities = (boolean) hasNewEntitiesMethod.invoke(null, entities);

        assertThat(hasNewEntities).isEqualTo(result);
    }

    private Method getMethodByName(Class<?> clazz, String methodName) {
        return Arrays.stream(clazz.getMethods())
                .filter(m -> m.getName().equals(methodName))
                .findAny().orElseThrow();
    }

    static Stream<Arguments> hasNewEntitiesArgs() {
        var newEntity = Mockito.mock(BaseEntity.class);
        when(newEntity.getUuid()).thenReturn(null);
        var oldEntity = Mockito.mock(BaseEntity.class);
        when(oldEntity.getUuid()).thenReturn(UUID.randomUUID());

        return Stream.of(
                arguments(List.of(newEntity, newEntity), true),
                arguments(List.of(newEntity, oldEntity), true),
                arguments(List.of(oldEntity, oldEntity), false)
        );
    }

    @Test
    @DisplayName("Method isValidCollection accepts a collection of any entities as a first param")
    void isValidCollectionMethodFirstParamIsAGenericCollectionOfEntities() {
        var isValidCollectionMethod = getMethodByName(PersistenceUtil.class, "isValidCollection");

        var entitiesParam = isValidCollectionMethod.getParameters()[0];

        assertThat(entitiesParam.getParameterizedType().getTypeName())
                .isEqualTo(String.format("%s<? extends %s>", Collection.class.getTypeName(), BaseEntity.class.getTypeName()));
    }

    @Test
    @DisplayName("Method isValidCollection accepts a predicate of any BaseEntity superclasses as a second param")
    void isValidCollectionMethodSecondParamIsAnyBaseEntitySuperClass() {
        var isValidCollectionMethod = getMethodByName(PersistenceUtil.class, "isValidCollection");

        var validationPredicate = isValidCollectionMethod.getParameters()[1];

        assertThat(validationPredicate.getParameterizedType().getTypeName())
                .isEqualTo(String.format("%s<? super %s>", Predicate.class.getTypeName(), BaseEntity.class.getTypeName()));
    }

    @Test
    @DisplayName("hasDuplicates is a generic method")
    void hasDuplicatesIsAGenericMethod() {
        var hasDuplicatesMethod = getMethodByName(PersistenceUtil.class, "hasDuplicates");

        assertThat(hasDuplicatesMethod.getTypeParameters()).hasSize(1);
    }

    @Test
    @DisplayName("hasDuplicates type parameter is called \"T\"")
    void hasDuplicatesTypeParameterIsCalledT() {
        var hasDuplicatesMethod = getMethodByName(PersistenceUtil.class, "hasDuplicates");
        var typeParameter = hasDuplicatesMethod.getTypeParameters()[0];

        assertThat(typeParameter.getName()).isEqualTo("T");
    }

    @Test
    @DisplayName("hasDuplicates type parameter is bounded by BaseEntity")
    void hasDuplicatesTypeParameterIsBoundedByBaseEntity() {
        var hasDuplicatesMethod = getMethodByName(PersistenceUtil.class, "hasDuplicates");
        var typeParameter = hasDuplicatesMethod.getTypeParameters()[0];
        Type bound = typeParameter.getBounds()[0];

        assertThat(bound.getTypeName()).isEqualTo(BaseEntity.class.getTypeName());
    }

    @ParameterizedTest
    @MethodSource("hasDuplicatesArgs")
    @DisplayName("hasDuplicates checks entity duplicates by UUID")
    @SneakyThrows
    void hasDuplicatesChecksEntitiesByUUID(List<? extends BaseEntity> entities, BaseEntity targetEntity, Boolean hasDuplicates) {
        var hasDuplicatesMethod = getMethodByName(PersistenceUtil.class, "hasDuplicates");

        var result = hasDuplicatesMethod.invoke(null, entities, targetEntity);

        assertThat(result).isEqualTo(hasDuplicates);
    }

    static Stream<Arguments> hasDuplicatesArgs() {
        var uniqueEntity = Mockito.mock(BaseEntity.class);
        when(uniqueEntity.getUuid()).thenReturn(UUID.randomUUID());
        var duplicateId = UUID.randomUUID();
        var duplicateEntity1 = Mockito.mock(BaseEntity.class);
        when(duplicateEntity1.getUuid()).thenReturn(duplicateId);
        var duplicateEntity2 = Mockito.mock(BaseEntity.class);
        when(duplicateEntity2.getUuid()).thenReturn(duplicateId);

        return Stream.of(
                arguments(List.of(uniqueEntity, duplicateEntity1), uniqueEntity, false),
                arguments(List.of(uniqueEntity, duplicateEntity1, duplicateEntity2), duplicateEntity1, true),
                arguments(List.of(duplicateEntity1, duplicateEntity2), duplicateEntity1, true)
        );
    }

    @Test
    @DisplayName("findMostRecentlyCreatedEntity is a generic method that accepts a collection of entities")
    void findMostRecentlyCreatedEntityIsAGenericMethod() {
        var hasDuplicatesMethod = getMethodByName(PersistenceUtil.class, "findMostRecentlyCreatedEntity");
        var typeParameter = hasDuplicatesMethod.getTypeParameters()[0];
        var bound = typeParameter.getBounds()[0];

        assertThat(typeParameter.getName()).isEqualTo("T");
        assertThat(bound.getTypeName()).isEqualTo(BaseEntity.class.getTypeName());
    }

    @ParameterizedTest
    @MethodSource("findMostRecentlyCreatedEntityArgs")
    @DisplayName("findMostRecentlyCreatedEntity returns the most recently created entity")
    @SneakyThrows
    void findMostRecentlyCreatedEntityReturnsEntityWithMaxCreatedOnValue(List<? extends BaseEntity> entities,
                                                                         BaseEntity mostRecentlyCreatedEntity) {
        var findMostRecentlyCreatedEntityMethod = getMethodByName(PersistenceUtil.class, "findMostRecentlyCreatedEntity");

        var result = findMostRecentlyCreatedEntityMethod.invoke(null, entities);

        assertThat(result).isEqualTo(mostRecentlyCreatedEntity);
    }

    static Stream<Arguments> findMostRecentlyCreatedEntityArgs() {
        var yearAgoEntity = Mockito.mock(BaseEntity.class);
        when(yearAgoEntity.getCreatedOn()).thenReturn(LocalDateTime.now().minus(1, YEARS));
        var monthAgoEntity = Mockito.mock(BaseEntity.class);
        when(monthAgoEntity.getCreatedOn()).thenReturn(LocalDateTime.now().minus(1, MONTHS));
        var dayAgoEntity = Mockito.mock(BaseEntity.class);
        when(dayAgoEntity.getCreatedOn()).thenReturn(LocalDateTime.now().minus(1, DAYS));

        return Stream.of(
                arguments(List.of(yearAgoEntity, monthAgoEntity, dayAgoEntity), dayAgoEntity),
                arguments(List.of(yearAgoEntity, monthAgoEntity), monthAgoEntity),
                arguments(List.of(yearAgoEntity, dayAgoEntity), dayAgoEntity),
                arguments(List.of(dayAgoEntity, monthAgoEntity), dayAgoEntity)
        );
    }

    @Test
    @DisplayName("findMostRecentlyCreatedEntity throws exception when collection is empty")
    @SneakyThrows
    void findMostRecentlyCreatedEntityReturnsEntityThrowsException() {
        var findMostRecentlyCreatedEntityMethod = getMethodByName(PersistenceUtil.class, "findMostRecentlyCreatedEntity");

        assertThatThrownBy(() -> findMostRecentlyCreatedEntityMethod.invoke(null, Collections.emptyList()))
                .hasCauseInstanceOf(NoSuchElementException.class);
    }

}

package com.bobocode.se;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Field;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class RandomFieldComparatorTest {

    private final RandomFieldComparator<Account> randomFieldComparator = new RandomFieldComparator<>(Account.class);

    @Test
    @Order(1)
    @DisplayName("Constructor throws an exception when parameter is null")
    void classDoesNotApplyNullInConstructor() {
        assertThrows(NullPointerException.class, () -> new RandomFieldComparator<>(null));
    }

    @Test
    @Order(2)
    @SneakyThrows
    @DisplayName("Constructor throws an exception when the target type has no Comparable fields")
    void constructorThrowsExceptionIfNoComparableFieldsInProvidedType() {
        assertThrows(IllegalArgumentException.class, () -> new RandomFieldComparator<>(ClassWithNotComparableField.class));
    }

    @Test
    @Order(3)
    @DisplayName("Method 'compare' throws an exception when any parameter is null")
    void compareWhenFirstParameterAreNull() {

        assertThrows(NullPointerException.class, () -> randomFieldComparator.compare(null, new Account()));
        assertThrows(NullPointerException.class, () -> randomFieldComparator.compare(new Account(), null));
    }

    @Test
    @Order(4)
    @DisplayName("Method 'compare' returns 0 when field values of both objects are null")
    void compareWhenBothFieldValuesIsNull() {
        setFieldToCompare("lastName", Account.class);
        int compareResult = randomFieldComparator.compare(new Account(), new Account());

        assertThat(compareResult).isZero();
    }

    @Test
    @Order(5)
    @DisplayName("Method compare returns positive int when the first field value is null")
    void compareWhenFieldValuesOfFirstObjectIsNull() {
        Account emptyAccount = new Account();
        Account account = new Account("Sibma", "LoinKing", "simba-bimba@gmail.com", 14);
        setFieldToCompare("email", Account.class);//set field to compare explicitly as there are int field which has default value 0
        int compareResult = randomFieldComparator.compare(emptyAccount, account);

        assertThat(compareResult).isPositive();
    }

    @Test
    @Order(6)
    @DisplayName("Method compare returns negative int when the second field value is null")
    void compareWhenFieldValuesOfSecondObjectIsNull() {
        Account account = new Account("Mufasa", "LoinKing", "simba-bimba@gmail.com", 47);
        Account emptyAccount = new Account();
        setFieldToCompare("firstName", Account.class);
        int compareResult = randomFieldComparator.compare(account, emptyAccount);

        assertThat(compareResult).isNegative();
    }

    @Test
    @Order(7)
    @SneakyThrows
    @DisplayName("Method 'compare' returns positive int when the first value is greater")
    void compareWhenFieldValueOfFirstObjectIsGreater() {
        var fieldToCompareName = "firstName";
        Account account1 = new Account();
        Account account2 = new Account();
        Field fieldToCompareAccount = account1.getClass().getDeclaredField(fieldToCompareName);
        fieldToCompareAccount.setAccessible(true);

        fieldToCompareAccount.set(account1, "Bob");
        fieldToCompareAccount.set(account2, "Alice");

        setFieldToCompare(fieldToCompareName, Account.class);
        int compareResult = randomFieldComparator.compare(account1, account2);

        assertThat(compareResult).isPositive();
    }

    @Test
    @Order(8)
    @SneakyThrows
    @DisplayName("Method 'compare' returns negative int when the first value is smaller")
    void compareWhenFieldValueOfSecondObjectIsGreater() {
        var fieldToCompareName = "firstName";
        Account account1 = new Account();
        Account account2 = new Account();
        Field fieldToCompareAccount = account1.getClass().getDeclaredField(fieldToCompareName);
        fieldToCompareAccount.setAccessible(true);

        fieldToCompareAccount.set(account1, "Alice");
        fieldToCompareAccount.set(account2, "Bob");

        setFieldToCompare(fieldToCompareName, Account.class);
        int compareResult = randomFieldComparator.compare(account1, account2);

        assertThat(compareResult).isNegative();
    }

    @Test
    @Order(9)
    @SneakyThrows
    @DisplayName("Method 'compare' returns zero when the field values are equal")
    void compareWhenFieldValuesOfObjectsAreEqual() {
        var fieldToCompareName = "firstName";
        Account account1 = new Account();
        Account account2 = new Account();
        Field fieldToCompareAccount = account1.getClass().getDeclaredField(fieldToCompareName);
        fieldToCompareAccount.setAccessible(true);

        fieldToCompareAccount.set(account1, "Carol");
        fieldToCompareAccount.set(account2, "Carol");

        setFieldToCompare(fieldToCompareName, Account.class);
        int compareResult = randomFieldComparator.compare(account1, account2);

        assertThat(compareResult).isZero();
    }

    @Test
    @Order(10)
    @SneakyThrows
    @DisplayName("Method 'compare' returns positive int when the first primitive value is greater")
    void comparePrimitivesWhenFieldValueOfFirstObjectIsGreater() {
        var fieldToCompareName = "age";
        Account account1 = new Account();
        Account account2 = new Account();
        Field fieldToCompareAccount = account1.getClass().getDeclaredField(fieldToCompareName);
        fieldToCompareAccount.setAccessible(true);

        fieldToCompareAccount.setInt(account1, 7);
        fieldToCompareAccount.setInt(account2, 3);

        setFieldToCompare(fieldToCompareName, Account.class);
        int compareResult = randomFieldComparator.compare(account1, account2);

        assertThat(compareResult).isPositive();
    }

    @Test
    @Order(11)
    @SneakyThrows
    @DisplayName("Method 'compare' returns zero when the primitive field values are equal")
    void comparePrimitivesWhenFieldValuesOfObjectsAreEqual() {
        var fieldToCompareName = "age";
        Account account1 = new Account();
        Account account2 = new Account();
        Field fieldToCompareAccount = account1.getClass().getDeclaredField(fieldToCompareName);
        fieldToCompareAccount.setAccessible(true);

        fieldToCompareAccount.setInt(account1, 15);
        fieldToCompareAccount.setInt(account2, 15);

        setFieldToCompare(fieldToCompareName, Account.class);
        int compareResult = randomFieldComparator.compare(account1, account2);

        assertThat(compareResult).isZero();
    }

    @Test
    @Order(12)
    @SneakyThrows
    @DisplayName("Method 'compare' returns negative int when the first primitive value is smaller")
    void comparePrimitivesWhenFieldValueOfSecondObjectIsGreater() {
        var fieldToCompareName = "age";
        Account account1 = new Account();
        Account account2 = new Account();
        Field fieldToCompareAccount = account1.getClass().getDeclaredField(fieldToCompareName);
        fieldToCompareAccount.setAccessible(true);

        fieldToCompareAccount.setInt(account1, 4);
        fieldToCompareAccount.setInt(account2, 8);

        setFieldToCompare(fieldToCompareName, Account.class);
        int compareResult = randomFieldComparator.compare(account1, account2);

        assertThat(compareResult).isNegative();
    }

    @Test
    @Order(13)
    @SneakyThrows
    @DisplayName("Method 'getComparingFieldName' returns the name of randomly-chosen field to be compared")
    void getComparingFieldName() {
        var fieldToCompareName = "lastName";
        setFieldToCompare(fieldToCompareName, Account.class);

        assertEquals(fieldToCompareName, randomFieldComparator.getComparingFieldName());
    }

    @Test
    @Order(14)
    @SneakyThrows
    @DisplayName("Method toString is properly overridden")
    void toStringOverriding() {
        var expectedString = "Random field comparator of class 'Account' is comparing 'email'";
        var fieldToCompareName = "email";
        setFieldToCompare(fieldToCompareName, Account.class);

        assertEquals(expectedString, randomFieldComparator.toString());
    }

    @SneakyThrows
    private <T> void setFieldToCompare(String fieldName, Class<T> classType) {
        Field fieldToCompare = Arrays.stream(randomFieldComparator.getClass().getDeclaredFields())
                .filter(f -> f.getType().equals(Field.class))
                .findAny()
                .orElseThrow();
        fieldToCompare.setAccessible(true);
        fieldToCompare.set(randomFieldComparator, classType.getDeclaredField(fieldName));
    }

    private static class EmptyClass {

    }

    @AllArgsConstructor
    private static class ClassWithNotComparableField {

        private Object field;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    private static class Account {

        private String firstName;
        private String lastName;
        private String email;
        private int age;
    }
}
package com.bobocode.se;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;
import java.lang.reflect.Field;
import lombok.AllArgsConstructor;
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
    @DisplayName("constructor should throw exception if parameter is null")
    void classDoesNotApplyNullInConstructor() {
        assertThrows(NullPointerException.class, () -> new RandomFieldComparator<>(null));
    }

    @Test
    @Order(2)
    @DisplayName("constructor should throw exception if provided type does not contain any fields")
    void constructorThrowsExceptionIfNoFieldsInProvidedType() {
        assertThrows(IllegalArgumentException.class, () -> new RandomFieldComparator<>(EmptyClass.class));
    }

    @Test
    @Order(3)
    @SneakyThrows
    @DisplayName("target type must contain at least one field which implements Comparable interface")
    void constructorThrowsExceptionIfNoComparableFieldsInProvidedType() {
        assertThrows(IllegalArgumentException.class, () -> new RandomFieldComparator<>(ClassWithNotComparableField.class));
    }

    @Test
    @Order(4)
    @DisplayName("'compare' should throw exception if any parameter is null")
    void compareWhenFirstParameterAreNull() {

        assertThrows(NullPointerException.class, () -> randomFieldComparator.compare(null, new Account()));
        assertThrows(NullPointerException.class, () -> randomFieldComparator.compare(new Account(), null));
    }

    @Test
    @Order(5)
    @DisplayName("'compare' should return 0 if field values of both objects are null")
    void compareWhenBothFieldValuesIsNull() {
        var emptyAccount1 = new Account();
        var emptyAccount2 = new Account();
        //set balance null as balance field has a default value
        emptyAccount1.setBalance(null);
        emptyAccount2.setBalance(null);

        int compareResult = randomFieldComparator.compare(new Account(), new Account());

        assertEquals(0, compareResult);
    }

    @Test
    @Order(6)
    @DisplayName("'compare' treats null value greater than a non-null value, so it should return 1 if field value of " +
            "first object is null")
    void compareWhenFieldValuesOfFirstObjectIsNull() {
        Account account = Accounts.generateAccount();
        account.setId(1L);
        Account emptyAccount = new Account();
        emptyAccount.setBalance(null);
        int compareResult = randomFieldComparator.compare(emptyAccount, account);

        assertEquals(1, compareResult);
    }

    @Test
    @Order(7)
    @DisplayName("'compare' treats null value greater than a non-null value, so it should return -1 if field value of " +
            "second object is null")
    void compareWhenFieldValuesOfSecondObjectIsNull() {
        Account account = Accounts.generateAccount();
        account.setId(1L);
        Account emptyAccount = new Account();
        emptyAccount.setBalance(null);
        int compareResult = randomFieldComparator.compare(account, emptyAccount);

        assertEquals(-1, compareResult);
    }

    @Test
    @Order(8)
    @SneakyThrows
    @DisplayName("'compare' should return 1 if field value of first object is greater")
    void compareWhenFieldValueOfFirstObjectIsGrater() {
        var fieldToCompareName = "firstName";
        Account account1 = Accounts.generateAccount();
        Account account2 = Accounts.generateAccount();
        Field fieldToCompareAccount = account1.getClass().getDeclaredField(fieldToCompareName);
        fieldToCompareAccount.setAccessible(true);

        fieldToCompareAccount.set(account1, "Bob");
        fieldToCompareAccount.set(account2, "Alice");

        setFieldToCompare(fieldToCompareName, Account.class);
        int compareResult = randomFieldComparator.compare(account1, account2);

        assertEquals(1, compareResult);
    }

    @Test
    @Order(9)
    @SneakyThrows
    @DisplayName("'compare' should return -1 if field value of second object is greater")
    void compareWhenFieldValueOfSecondObjectIsGrater() {
        var fieldToCompareName = "firstName";
        Account account1 = Accounts.generateAccount();
        Account account2 = Accounts.generateAccount();
        Field fieldToCompareAccount = account1.getClass().getDeclaredField(fieldToCompareName);
        fieldToCompareAccount.setAccessible(true);

        fieldToCompareAccount.set(account1, "Alice");
        fieldToCompareAccount.set(account2, "Bob");

        setFieldToCompare(fieldToCompareName, Account.class);
        int compareResult = randomFieldComparator.compare(account1, account2);

        assertEquals(-1, compareResult);
    }

    @Test
    @Order(10)
    @SneakyThrows
    @DisplayName("'compare' should return -1 if field value of second object is greater")
    void compareWhenFieldValuesOfObjectsAreEqual() {
        var fieldToCompareName = "firstName";
        Account account1 = Accounts.generateAccount();
        Account account2 = Accounts.generateAccount();
        Field fieldToCompareAccount = account1.getClass().getDeclaredField(fieldToCompareName);
        fieldToCompareAccount.setAccessible(true);

        fieldToCompareAccount.set(account1, "Carol");
        fieldToCompareAccount.set(account2, "Carol");

        setFieldToCompare(fieldToCompareName, Account.class);
        int compareResult = randomFieldComparator.compare(account1, account2);

        assertEquals(0, compareResult);
    }

    @Test
    @Order(11)
    @SneakyThrows
    @DisplayName("'getComparingFieldName' should return the name of randomly-chosen field to be compared")
    void getComparingFieldName() {
        var fieldToCompareName = "lastName";
        setFieldToCompare(fieldToCompareName, Account.class);

        assertEquals(fieldToCompareName, randomFieldComparator.getComparingFieldName());
    }

    @Test
    @Order(12)
    @SneakyThrows
    @DisplayName("'toString' should return the string - \"Random field comparator of class '%s' is comparing '%s'\"")
    void toStringOverriding() {
        var expectedString = "Random field comparator of class 'Account' is comparing 'email'";
        var fieldToCompareName = "email";
        setFieldToCompare(fieldToCompareName, Account.class);

        assertEquals(expectedString, randomFieldComparator.toString());
    }

    @SneakyThrows
    private <T> void setFieldToCompare(String fieldName, Class<T> classType) {
        Field fieldToCompare = randomFieldComparator.getClass().getDeclaredField("fieldToCompare");
        fieldToCompare.setAccessible(true);
        fieldToCompare.set(randomFieldComparator, classType.getDeclaredField(fieldName));
    }

    private static class EmptyClass {

    }

    @AllArgsConstructor
    private static class ClassWithNotComparableField {

        private Object field;
    }
}
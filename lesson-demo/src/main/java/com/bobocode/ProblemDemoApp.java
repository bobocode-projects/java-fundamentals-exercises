package com.bobocode;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;
import lombok.ToString;

import java.util.List;

public class ProblemDemoApp {
    public static void main(String[] args) {
        var list1 = List.of(5, 1, 6, 9);
        List<Integer> list2 = List.of(9, 14, 644, 9);
        int maxInt = max(list2);
        
        var list3 = List.of("Hello", "Aloha", "Hey");

        Person p = new Person("Serhii", 32);
        Employee e = new Employee("Marianna", 26);
        Person anotherPerson = e;
//        Employee anotherEmployee = p;
        
        // generics are invariant by default 
        List<Person> personList = List.of(p);
        List<Employee> employeeList = List.of(e);
        
        // making generic class convariant
        List<? extends Person> anotherPersonList = employeeList;

        // making generic class contravariant
        List<? super Employee> anotherEmployeeList = personList;
        
        

        List<Employee> employees = List.of(
                new Employee("Serhii", 32), 
                new Employee("Marianna", 26), 
                new Employee("Roman", 36)
        );
        Employee employee = max(employees);
        System.out.println(employee);
    }

    @ToString
    static class Person implements Comparable<Person> {
        private String firstName;
        private int age;

        public Person(String firstName, int age) {
            this.age = age;
            this.firstName = firstName;
        }

        @Override
        public int compareTo(Person o) {
            return Integer.compare(this.age, o.age);
        }
    }

    static class Employee extends Person {

        public Employee(String firstName, int age) {
            super(firstName, age);
        }
    }
    
    // method-level type parametrisation
    // bounded types
    // class-level type parametrisation
    // making generics covariant/contravariant -> wildcards and extends/super 
    // PECS -> Producer extends, Consumer super
    private static <T extends Comparable<? super T>> T max(List<T> list) {
        T maxElement = list.get(0);
        for (var e : list) {
            if (e.compareTo(maxElement) > 0) {
                maxElement = e;
            }
        }
        return maxElement;
    }

}

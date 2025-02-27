package com.bobocode.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "email")
public class Account {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private Sex sex;
    private LocalDate creationDate;
    private BigDecimal balance = BigDecimal.ZERO;

    public BigDecimal getBalance() {
        return balance;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Sex getSex() {
        return sex;
    }

    public Long getId() {
        return id;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}


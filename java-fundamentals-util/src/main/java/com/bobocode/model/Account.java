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

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Sex getSex() {
        return sex;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}


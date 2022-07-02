package com.bobocode;

public class Developer extends Partner {
    private String position;

    public Developer(String position, Seniority seniority) {
        super(seniority);
        this.position = position;
    }
}

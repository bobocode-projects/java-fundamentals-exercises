package com.bobocode;

public class Partner implements Comparable<Partner> {
    private Seniority seniority;

    public Partner(Seniority seniority) {
        this.seniority = seniority;
    }

    @Override
    public int compareTo(Partner o) {
        return this.seniority.compareTo(o.seniority);
    }

    enum Seniority {
        ASSOCIATE, JUNIOR, PARTNER, SENIOR
    }
}

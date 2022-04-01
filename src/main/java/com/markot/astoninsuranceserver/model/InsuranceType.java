package com.markot.astoninsuranceserver.model;

/**
 * Created by Martin Kotulac
 * on 31/03/2022
 */
public enum InsuranceType {
    SHORT_TERM("Krátkodobé"),
    YEAR_ROUND("Celoročné");

    private final String descripiton;

    InsuranceType(String descripiton) {
        this.descripiton = descripiton;
    }

    public String getDescripiton() {
        return descripiton;
    }
}

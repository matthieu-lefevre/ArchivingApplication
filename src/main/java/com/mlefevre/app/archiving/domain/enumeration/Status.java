package com.mlefevre.app.archiving.domain.enumeration;

public enum Status {

    SUCCESS("OK"),
    FAIL("KO");

    private String value;

    private Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}

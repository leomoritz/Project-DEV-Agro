package com.senai.devagro.devagro.model.enums;

public enum Gender {

    MALE("Male"),
    FEMALE("Female");

    private final String genderDescription;

    Gender(String genderDescription) {
        this.genderDescription = genderDescription;
    }

    public String getGenderDescription() {
        return genderDescription;
    }
}

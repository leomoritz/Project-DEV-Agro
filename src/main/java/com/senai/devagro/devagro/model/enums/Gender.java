package com.senai.devagro.devagro.model.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {

    MALE("Male"),
    FEMALE("Female"),
    UNKNOW("UNKNOW");

    private final String genderDescription;

    Gender(String genderDescription) {
        this.genderDescription = genderDescription;
    }

    public String getGenderDescription() {
        return genderDescription;
    }
}

package com.senai.devagro.devagro.model.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Gender {

    MALE("Male"),
    FEMALE("Female"),
    @JsonEnumDefaultValue // anotação utilizada para definir um valor default caso o usuário digite algum enum inválido pelo REST.
    UNKNOW("UNKNOW");

    private final String genderDescription;

    Gender(String genderDescription) {
        this.genderDescription = genderDescription;
    }

    public String getGenderDescription() {
        return genderDescription;
    }
}

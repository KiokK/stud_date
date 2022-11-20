package com.kiok.Models;

public enum TypeOfCertification {
    DIFF("диф. зач."), TEST("зачет"), EXAM("экз.");

    TypeOfCertification(String type) {
        typeOfTest = type;
    }
    private String typeOfTest;

    public String getTypeOfTest() {
        return typeOfTest;
    }

}

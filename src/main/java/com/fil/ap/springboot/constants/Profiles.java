package com.fil.ap.springboot.constants;

public enum Profiles {

    DEVELOPING("DEVELOPING"),

    UT("UT"),

    SIT("SIT"),

    UAT("UAT"),

    PRD("PRD");

    private String name;

    private Profiles(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }
}

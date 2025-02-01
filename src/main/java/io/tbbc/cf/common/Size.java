package io.tbbc.cf.common;

public enum Size {
    SMALL("s", "small"),
    MEDIUM("m", "medium"),
    LARGE("l", "large"),
    EXTRA_LARGE("xl", "extra large");

    private final String egoInitial;
    private final String egoFullString;

    Size(String egoInitial, String egoFullString) {
        this.egoInitial = egoInitial;
        this.egoFullString = egoFullString;
    }

    public String egoInitial() {
        return egoInitial;
    }

    public String egoFullString() {
        return egoFullString;
    }
}

package com.tanvircodder.animationlist.utility;

public class Util {
    private String Type;
    private String description;
    private String englishTitle;

    public Util(String type, String description, String englishTitle) {
        Type = type;
        this.description = description;
        this.englishTitle = englishTitle;
    }

    public String getType() {
        return Type;
    }

    public String getDescription() {
        return description;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }
}

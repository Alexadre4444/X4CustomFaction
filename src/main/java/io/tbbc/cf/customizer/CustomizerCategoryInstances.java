package io.tbbc.cf.customizer;

public class CustomizerCategoryInstances {
    public static final CustomizerCategory BASIC = new CustomizerCategory(new CustomizerCategoryName("basic"), "Basic");
    public static final CustomizerCategory ADVANCED = new CustomizerCategory(new CustomizerCategoryName("advanced"), "Advanced");
    public static final CustomizerCategory EXPERT = new CustomizerCategory(new CustomizerCategoryName("expert"), "Expert");

    private CustomizerCategoryInstances() {
    }
}

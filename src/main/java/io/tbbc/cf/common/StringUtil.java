package io.tbbc.cf.common;

public class StringUtil {
    private StringUtil() {
    }

    public static String normalizeLabel(String label) {
        label = label != null ? label.strip() : "No label";
        return label.substring(0, 1).toUpperCase() + label.substring(1);
    }

    public static String normalizeDescription(String description) {
        description = description != null ? description.strip() : "No description.";
        return description.substring(0, 1).toUpperCase() + description.substring(1);
    }
}

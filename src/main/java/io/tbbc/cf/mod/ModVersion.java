package io.tbbc.cf.mod;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public record ModVersion(long version) {
    public String egoVersion() {
        NumberFormat numberFormat = DecimalFormat.getIntegerInstance();
        numberFormat.setMinimumIntegerDigits(3);
        return numberFormat.format(version);
    }
}

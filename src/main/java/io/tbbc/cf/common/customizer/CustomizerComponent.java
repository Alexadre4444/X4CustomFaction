package io.tbbc.cf.common.customizer;

import java.util.List;

public record CustomizerComponent(String name, String label, List<Customizer> customizers) {
}

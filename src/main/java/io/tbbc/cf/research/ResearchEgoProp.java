package io.tbbc.cf.research;

import io.tbbc.cf.common.LangEntry;

public record ResearchEgoProp(String name, int labelSection, int descriptionSection, LangEntry langEntryName,
                              LangEntry langEntryDescription) {
}

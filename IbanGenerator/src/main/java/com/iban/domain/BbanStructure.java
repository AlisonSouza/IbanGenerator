package com.iban.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

import com.iban.domain.enums.CountryCode;


/**
 * Class which represents bban basic structure
 */
public class BbanStructure {

    private final BbanStructureEntry[] entries;

    private BbanStructure(final BbanStructureEntry... entries) {
        this.entries = entries;
    }

    private static final EnumMap<CountryCode, BbanStructure> structures;

    static {
        structures = new EnumMap<CountryCode, BbanStructure>(CountryCode.class);

        structures.put(CountryCode.AT,
                new BbanStructure(
                        BbanStructureEntry.bankCode(5, 'n'),
                        BbanStructureEntry.accountNumber(11, 'n')));

        structures.put(CountryCode.DE,
                new BbanStructure(
                        BbanStructureEntry.bankCode(8, 'n'),
                        BbanStructureEntry.accountNumber(10, 'n')));

        structures.put(CountryCode.NL,
                new BbanStructure(
                        BbanStructureEntry.bankCode(4, 'a'),
                        BbanStructureEntry.accountNumber(10, 'n')));
    }

    public static BbanStructure forCountry(final CountryCode countryCode) {
        return structures.get(countryCode);
    }

    public List<BbanStructureEntry> getEntries() {
        return Collections.unmodifiableList(Arrays.asList(entries));
    }
}

package com.iban.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.iban.domain.enums.BbanEntryType;

/**
 * Bban Structure Entry representation.
 */
public class BbanStructureEntry {

    private final BbanEntryType entryType;
    private final EntryCharacterType characterType;
    private final int length;

    private static Map<EntryCharacterType, char[]> charByCharacterType;
    private final Random random = new Random();

    static {
        charByCharacterType = new HashMap<EntryCharacterType, char[]>();
        StringBuilder charTypeN = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ch++) {
            charTypeN.append(ch);
        }
        charByCharacterType.put(EntryCharacterType.n, charTypeN.toString().toCharArray());

        StringBuilder charTypeA = new StringBuilder();
        for (char ch = 'A'; ch <= 'Z'; ++ch) {
            charTypeA.append(ch);
        }
        charByCharacterType.put(EntryCharacterType.a, charTypeA.toString().toCharArray());

        charByCharacterType.put(EntryCharacterType.c, (charTypeN.toString() + charTypeA.toString()).toCharArray());
    }

    private BbanStructureEntry(final BbanEntryType entryType,
                       final EntryCharacterType characterType,
                       final int length) {
        this.entryType = entryType;
        this.characterType = characterType;
        this.length = length;
    }

    public static BbanStructureEntry bankCode(final int length, final char characterType) {
        return new BbanStructureEntry(BbanEntryType.bank_code,
                EntryCharacterType.valueOf(String.valueOf(characterType)), length);
    }

    public static BbanStructureEntry accountNumber(final int length, final char characterType) {
        return new BbanStructureEntry(BbanEntryType.account_number,
                EntryCharacterType.valueOf(String.valueOf(characterType)), length);
    }

    public BbanEntryType getEntryType() {
        return entryType;
    }

    public EntryCharacterType getCharacterType() {
        return characterType;
    }

    public int getLength() {
        return length;
    }

    public enum EntryCharacterType {
        n,  // Digits (numeric characters 0 to 9 only)
        a,  // Upper case letters (alphabetic characters A-Z only)
        c  // upper and lower case alphanumeric characters (A-Z, a-z and 0-9)
    }

    public String getRandom() {
        StringBuilder s = new StringBuilder("");
        char[] charChoices = charByCharacterType.get(characterType);
        if (charChoices == null) {
            throw new RuntimeException(String.format("programmer has not implemented choices for character type %s",
                    characterType.name()));
        }
        for (int i = 0; i < getLength(); i++) {
            s.append(charChoices[random.nextInt(charChoices.length)]);
        }
        return s.toString();
    }
}


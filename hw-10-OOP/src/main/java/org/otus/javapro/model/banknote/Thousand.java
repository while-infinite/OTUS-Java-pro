package org.otus.javapro.model.banknote;

import java.util.List;

public class Thousand extends BanknoteCell {
    private static final int NOMINAL = 1000;

    public Thousand(List<Integer> banknotes) {
        super(banknotes, NOMINAL);
    }
}

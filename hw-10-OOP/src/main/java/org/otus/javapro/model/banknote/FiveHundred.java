package org.otus.javapro.model.banknote;

import java.util.List;

public class FiveHundred extends BanknoteCell {
    private static final int NOMINAL = 500;

    public FiveHundred(List<Integer> banknotes) {
        super(banknotes, NOMINAL);
    }
}

package org.otus.javapro.model.banknote;

import java.util.List;

public class TwoHundred extends BanknoteCell {
    private static final int NOMINAL = 200;

    public TwoHundred(List<Integer> banknotes) {
        super(banknotes, NOMINAL);
    }
}

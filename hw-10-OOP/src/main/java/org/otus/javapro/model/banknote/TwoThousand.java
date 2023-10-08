package org.otus.javapro.model.banknote;

import java.util.List;

public class TwoThousand extends BanknoteCell {
    private static final int NOMINAL = 2000;

    public TwoThousand(List<Integer> banknotes) {
        super(banknotes, NOMINAL);
    }
}

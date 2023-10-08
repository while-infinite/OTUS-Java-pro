package org.otus.javapro.model.banknote;

import java.util.List;

public class FiveThousand extends BanknoteCell {
    private static final int NOMINAL = 5000;

    public FiveThousand(List<Integer> banknotes) {
        super(banknotes, NOMINAL);
    }
}

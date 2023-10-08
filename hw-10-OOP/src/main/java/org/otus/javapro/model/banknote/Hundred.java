package org.otus.javapro.model.banknote;

import java.util.List;

public class Hundred extends BanknoteCell {
    private static final int NOMINAL = 100;

    public Hundred(List<Integer> banknotes) {
        super(banknotes, NOMINAL);
    }
}

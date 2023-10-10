package org.otus.javapro.model.banknote;

public enum Banknote {
    FIFTY(50),
    ONE_HUNDRED(100),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000),
    FIVE_THOUSAND(5000);

    final int nominal;
    Banknote(int nominal) {
        this.nominal = nominal;
    }
}

package org.otus.javapro;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.otus.javapro.model.Atm;
import org.otus.javapro.model.banknote.*;

import java.util.List;


public class Client {
    private static final Logger log = LoggerFactory.getLogger(Client.class);

    private static Atm atm;

    public static void main(String[] args) {

        fillAtm();
        log.info(() -> "Total sum after filling cells: " + atm.getTotalSum());
        log.info(() ->"-".repeat(150));
        try {
            log.info(() -> "Extract banknotes: " + atm.extractBanknotes(13900L));
        } catch (RuntimeException e) {
            log.error(() -> "Exception: " + e.fillInStackTrace());
        }
        log.info(() ->"-".repeat(150));
        log.info(() -> "Total sum after extracting banknotes " + atm.getTotalSum());
        log.info(() ->"-".repeat(150));
        log.info(() -> "Total sum of 50 - " + atm.getFifty().getTotalSum());
        log.info(() -> "Total sum of 100 - " + atm.getOneHundred().getTotalSum());
        log.info(() -> "Total sum of 500 - " + atm.getFiveHundred().getTotalSum());
        log.info(() -> "Total sum of 1000 - " + atm.getOneThousand().getTotalSum());
        log.info(() -> "Total sum of 5000 - " + atm.getFiveThousand().getTotalSum());
    }

    private static void fillAtm() {
        BanknoteCell fifty = new BanknoteCell(List.of(50, 50, 50), Banknote.FIFTY);
        BanknoteCell oneHundred = new BanknoteCell(List.of(100, 100, 100), Banknote.ONE_HUNDRED);
        BanknoteCell fiveHundred = new BanknoteCell(List.of(500, 500, 500), Banknote.FIVE_HUNDRED);
        BanknoteCell oneThousand = new BanknoteCell(List.of(1000, 1000, 1000), Banknote.ONE_THOUSAND);
        BanknoteCell fiveThousand = new BanknoteCell(List.of(5000, 5000, 5000), Banknote.FIVE_THOUSAND);

        atm = Atm.builder()
                .fifty(fifty)
                .oneHundred(oneHundred)
                .fiveHundred(fiveHundred)
                .oneThousand(oneThousand)
                .fiveThousand(fiveThousand)
                .build();
    }
}

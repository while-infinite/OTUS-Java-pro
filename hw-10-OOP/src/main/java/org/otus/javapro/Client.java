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
        log.info(() -> "Total sum of 100 - " + atm.getHundred().getTotalSum());
        log.info(() -> "Total sum of 200 - " + atm.getTwoHundred().getTotalSum());
        log.info(() -> "Total sum of 500 - " + atm.getFiveHundred().getTotalSum());
        log.info(() -> "Total sum of 1000 - " + atm.getThousand().getTotalSum());
        log.info(() -> "Total sum of 5000 - " + atm.getFiveThousand().getTotalSum());
    }

    private static void fillAtm() {
        Hundred hundredCell = new Hundred(List.of(100, 100, 100));
        TwoHundred twoHundred = new TwoHundred(List.of(200, 200, 200));
        FiveHundred fiveHundred = new FiveHundred(List.of(500, 500, 500));
        Thousand thousand = new Thousand(List.of(1000, 1000, 1000));
        TwoThousand twoThousand = new TwoThousand(List.of(2000, 2000, 2000));
        FiveThousand fiveThousand = new FiveThousand(List.of(5000, 5000, 5000));

        atm = Atm.builder()
                .hundred(hundredCell)
                .twoHundred(twoHundred)
                .fiveHundred(fiveHundred)
                .thousand(thousand)
                .twoThousand(twoThousand)
                .fiveThousand(fiveThousand)
                .build();
    }
}

package org.otus.javapro.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.otus.javapro.model.banknote.*;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Atm {
    private BanknoteCell fifty;
    private BanknoteCell oneHundred;
    private BanknoteCell fiveHundred;
    private BanknoteCell oneThousand;
    private BanknoteCell fiveThousand;


    public Long getTotalSum() {
       return this.getListOfCells().stream()
               .mapToLong(BanknoteCell::getTotalSum)
               .sum();
    }

    public Long extractBanknotes(Long amount) {
        if(amount > this.getTotalSum())
            throw new RuntimeException("Amount is exceed the total sum of banknotes in cell");
        if(amount % 50 != 0)
            throw new RuntimeException("The banknote must be a multiple of 50");
        var cells = this.getListOfCells();
        long remainAmount = amount;
        for (BanknoteCell cell : cells) {
            int nominal = cell.getNominal();
            while(remainAmount >= nominal && cell.getBanknoteCount() > 0){
                remainAmount -= nominal;
                cell.extractBanknotes((long) nominal);
            }
        }
        return amount;
    }

    private List<BanknoteCell> getListOfCells() {
        LinkedList<BanknoteCell> cells = new LinkedList<>();
        cells.add(fiveThousand);
        cells.add(oneThousand);
        cells.add(fiveHundred);
        cells.add(oneHundred);
        cells.add(fifty);
        return cells;
    }

}

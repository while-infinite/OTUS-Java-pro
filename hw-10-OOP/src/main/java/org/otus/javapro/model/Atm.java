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
    private Hundred hundred;
    private TwoHundred twoHundred;
    private FiveHundred fiveHundred;
    private Thousand thousand;
    private TwoThousand twoThousand;
    private FiveThousand fiveThousand;


    public Long getTotalSum() {
       return this.getListOfCells().stream()
               .mapToLong(BanknoteCell::getTotalSum)
               .sum();
    }

    public Long extractBanknotes(Long amount) {
        if(amount > this.getTotalSum())
            throw new RuntimeException("Amount is exceed the total sum of banknotes in cell");
        if(amount % 100 != 0)
            throw new RuntimeException("The banknote must be a multiple of 100");
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
        cells.add(twoThousand);
        cells.add(thousand);
        cells.add(fiveHundred);
        cells.add(twoHundred);
        cells.add(hundred);
        return cells;
    }

}

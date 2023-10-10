package org.otus.javapro.model.banknote;

import java.util.ArrayList;
import java.util.List;

public class BanknoteCell {
    protected List<Integer> cell;
    protected Banknote banknote;


    public BanknoteCell(List<Integer> banknotes, Banknote banknote) {
        this.banknote = banknote;
        this.fillCell(banknotes);
    }

    public Long getTotalSum() {
        return cell.stream()
                .mapToLong(Integer::longValue)
                .sum();
    }
    public void fillCell(List<Integer> banknotes) {
        var isWrongBanknote = banknotes.stream().anyMatch(bill -> bill != banknote.nominal);
        if(isWrongBanknote)
            throw new RuntimeException("Wrong banknote was putted");
        cell = new ArrayList<>(banknotes);
    }
    public void extractBanknotes(Long amount) {
        if(amount == null)
            throw  new IllegalArgumentException("Amount can't be null");
        var remainSum = getTotalSum() - amount;
        var remainBanknoteCount = remainSum/ banknote.nominal;
        var endIndex = (int) remainBanknoteCount;
        cell = cell.subList(0, endIndex);
    }

    public int getNominal() {
        return banknote.nominal;
    }

    public int getBanknoteCount() {
        return cell.size();
    }
}

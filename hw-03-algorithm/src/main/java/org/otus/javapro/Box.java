package org.otus.javapro;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.otus.javapro.fruits.Fruit;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Box<F extends Fruit> {

    private static final String CANT_BE_NULL = "Compared box can't be null";

    private List<F> fruits;

    public Box() {
        this.fruits = new ArrayList<>();
    }

    public void addFruit(F fruit) {
        fruits.add(fruit);
    }

    public int weight() {
        return fruits.stream()
                .mapToInt(Fruit::getWeight)
                .sum();
    }

    public boolean compare(Box<?> box) {
        if(box == null)
            throw new IllegalArgumentException(CANT_BE_NULL);
        return this.weight() == box.weight();
    }

    public void pourOver(Box<? super F> otherBox) {
        if(otherBox == null)
            throw new IllegalArgumentException(CANT_BE_NULL);
        if(otherBox != this) {
            fruits.forEach(otherBox::addFruit);
            fruits.clear();
        }
    }

}

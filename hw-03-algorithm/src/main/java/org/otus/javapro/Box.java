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

    public <T extends Fruit> boolean compare(Box<T> box) {
        return this.weight() == box.weight();
    }

    public void pourOver(Box<? super F> otherBox) {
        fruits.forEach(otherBox::addFruit);
        fruits.clear();
    }

}

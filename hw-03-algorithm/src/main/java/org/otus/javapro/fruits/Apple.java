package org.otus.javapro.fruits;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Apple extends Fruit{

    public Apple(int weight) {
        super(weight);
    }

}

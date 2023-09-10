package org.otus.javapro.fruits;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Orange extends Fruit{
    public Orange(int weight) {
        super(weight);
    }
}

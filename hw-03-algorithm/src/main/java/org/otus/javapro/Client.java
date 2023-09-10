package org.otus.javapro;

import lombok.extern.slf4j.Slf4j;
import org.otus.javapro.fruits.Apple;
import org.otus.javapro.fruits.Fruit;
import org.otus.javapro.fruits.Orange;

import java.util.Random;
import java.util.stream.Stream;

@Slf4j
public class Client {

    private static final Random random = new Random();
    public static void main(String[] args) {

        Box<Apple> apples = fillAppleBox();
        Box<Apple> applesSecond = fillAppleBox();
        Box<Orange> oranges = fillOrangeBox();
        Box<Fruit> fruits = fillFruitBox();

        log.info("\n" + "-".repeat(150));
        log.info("Apples in the box = {}", apples);
        log.info("Oranges in the box = {}", oranges);
        log.info("\n" + "-".repeat(150));

        log.info("\n" + "-".repeat(150));
        log.info("Weight of apples box = {}", apples.weight());
        log.info("Weight of oranges box = {}", oranges.weight());
        log.info("\n" + "-".repeat(150));

        log.info("\n" + "-".repeat(150));
        log.info("Compare box's weight = {}", apples.compare(oranges));
        log.info("\n" + "-".repeat(150));

        log.info("\n" + "-".repeat(150));
        log.info("One apple box pour over to another\n");
        log.info("Before:\n");
        log.info("Box one = {}",  apples.weight() + "\n");
        log.info("Box two = {}", applesSecond.weight() + "\n");
        apples.pourOver(applesSecond);
        log.info("After:\n");
        log.info("Box one = {}", apples.weight() + "\n");
        log.info("Box two = {}", applesSecond.weight() + "\n");
        log.info("\n" + "-".repeat(150));


        log.info("\n" + "-".repeat(150));
        log.info("Orange box pour over to fruits box\n");
        log.info("Before:\n");
        log.info("Box one = {}", oranges.weight() + "\n");
        log.info("Box two = {}", fruits.weight() + "\n");
        oranges.pourOver(fruits);
        log.info("After:\n");
        log.info("Box one = {}",  oranges.weight() + "\n");
        log.info("Box two = {}",  fruits.weight() + "\n");
        log.info("\n" + "-".repeat(150));
    }

    private static Box<Apple> fillAppleBox() {
        Box<Apple> apples = new Box<>();
        Stream.iterate(0, n -> n + 1)
                .limit(4)
                .forEach(i -> apples.addFruit(new Apple(random.nextInt(150 - 100) + 100)));
        return apples;
    }

    private static Box<Orange> fillOrangeBox() {
        Box<Orange> oranges = new Box<>();
        Stream.iterate(0, n -> n + 1)
                .limit(4)
                .forEach(i -> oranges.addFruit(new Orange(random.nextInt(150 - 100) + 100)));
        return oranges;
    }

    private static Box<Fruit> fillFruitBox() {
        Box<Fruit> fruits = new Box<>();
        fruits.addFruit(new Apple(105));
        fruits.addFruit(new Apple(114));
        fruits.addFruit(new Orange(115));
        fruits.addFruit(new Orange(118));
        return fruits;
    }
}
package org.otus.javapro;

import com.google.common.base.Strings;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;

public class HelloOtus {
    public static void main(String[] args) {


        System.out.println(Strings.repeat("_", 15));
        ImmutableList.of("I", "Like", "java")
                .stream()
                .map(name -> Strings.padStart(name, 15, '.'))
                .forEach(System.out::println);

        System.out.println(Strings.repeat("_", 15));
        ImmutableList.Builder<Integer> builder = ImmutableList.builder();
        for (int i = 0; i < 5; i++) {
            builder.add(i);
        }
        ImmutableList<Integer> list = builder.build();

        Multimap<String, String> map = HashMultimap.create();

        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key2", "value3");
        map.put("key3", "value4");
        System.out.println(map);



    }
}
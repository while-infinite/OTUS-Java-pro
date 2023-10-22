package ru.otus.processor;

import ru.otus.model.Message;

public class ProcessorSwapField implements Processor {

    @Override
    public Message process(Message message) {
        var temp = message.getField11();
       return message.toBuilder()
               .field11(message.getField12())
               .field12(temp)
               .build();
    }
}

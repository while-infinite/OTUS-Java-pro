package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    private final List<Message> messages;

    public HistoryListener() {
        this.messages = new ArrayList<>();
    }

    @Override
    public void onUpdated(Message msg) {
        var newMessage = this.copy(msg);
        messages.add(newMessage);
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return messages.stream()
                .filter(msg -> msg.getId() == id)
                .findFirst();
    }

    private Message copy(Message msg) {
        return new Message.Builder(msg.getId())
                .field1(msg.getField1())
                .field2(msg.getField2())
                .field3(msg.getField3())
                .field4(msg.getField4())
                .field5(msg.getField5())
                .field6(msg.getField6())
                .field7(msg.getField7())
                .field8(msg.getField8())
                .field9(msg.getField9())
                .field10(msg.getField10())
                .field11(msg.getField11())
                .field12(msg.getField12())
                .field13(copyObjectForMessage(msg.getField13()))
                .build();

    }

    private ObjectForMessage copyObjectForMessage(ObjectForMessage ofm) {
        var obj = new ObjectForMessage();
        obj.setData(List.copyOf(ofm.getData()));
        return obj;
    }
}

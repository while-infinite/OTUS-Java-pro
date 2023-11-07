package org.otus.javapro;

import org.otus.javapro.filter.ChatSessionFilter;
import org.otus.javapro.filter.MessageFilter;
import org.otus.javapro.filter.SmsFilter;
import org.otus.javapro.utils.JsonSerializer;


import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    private static final String JSON_SMS_PATH = "hw-15-serialyzation/src/main/resources/sms.json";
    private static final String JSON_FILTERED_SMS_PATH = "hw-15-serialyzation/src/main/resources/filter.json";

    public static void main(String[] args) throws IOException {
        //Read from homework json file
        Sms sms = JsonSerializer.readJson(Sms.class, JSON_SMS_PATH);
        List<ChatSessionFilter> filteredMessages = sms.getChatSessions().stream()
                .map(chatSessions ->
                        ChatSessionFilter.builder()
                                .chatIdentifier(chatSessions.getChatIdentifier())
                                .last(chatSessions.getMembers().stream().map(Member::getLast).toList())
                                .messages(toGroupedMessageFilter(chatSessions.getMessages())).build()
                )
                .toList();

        //Write to json file new list structure
        SmsFilter writeToJson = new SmsFilter(filteredMessages);
        JsonSerializer.writeJson(writeToJson, SmsFilter.class, JSON_FILTERED_SMS_PATH);

        //Write to json file new list structure
        SmsFilter readFromJson = JsonSerializer.readJson(SmsFilter.class, JSON_FILTERED_SMS_PATH);
        System.out.println(readFromJson);
    }

    private static Map<String, List<MessageFilter>> toGroupedMessageFilter(List<Message> messages) {
        return messages.stream()
                .map(Main::toMessageFilter)
                .collect(Collectors.groupingBy(MessageFilter::getBelongNumber))
                .entrySet()
                .stream()
                .peek(entry -> entry.getValue().sort(Comparator.comparing(MessageFilter::getSendDate)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static MessageFilter toMessageFilter(Message message) {
        return MessageFilter.builder()
                .sendDate(message.getSendDate())
                .text(message.getText())
                .belongNumber(message.getBelongNumber())
                .build();
    }


}
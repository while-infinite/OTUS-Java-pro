package org.otus.javapro.filter;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
public class SmsFilter implements Serializable {

    public SmsFilter(List<ChatSessionFilter> chatSessionFilterList) {
        this.chatSessionFilterList = chatSessionFilterList;
    }

    @SerializedName(value = "chat_session_filter")
    private List<ChatSessionFilter> chatSessionFilterList;

    @Override
    public String toString() {
        return "{" +
                "chatSessionFilterList=" + chatSessionFilterList +
                '}';
    }
}

package org.otus.javapro;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Sms implements Serializable {
    @SerializedName(value = "chat_sessions")
    private List<ChatSessions> chatSessions;

    @Override
    public String toString() {
        return "Sms{" +
                "chatSessions=" + chatSessions +
                '}';
    }
}

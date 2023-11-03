package org.otus.javapro;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ChatSessions implements Serializable {

    @SerializedName(value = "chat_id")
    private Long chatId;
    @SerializedName(value = "chat_identifier")
    private String chatIdentifier;
    @SerializedName(value = "display_name")
    private String displayName;
    @SerializedName(value = "is_deleted")
    private Boolean isDeleted;
    private List<Member> members;
    private List<Message> messages;

    @Override
    public String toString() {
        return "ChatSessions{" +
                "chatId=" + chatId +
                ", chatIdentifier='" + chatIdentifier + '\'' +
                ", displayName='" + displayName + '\'' +
                ", isDeleted=" + isDeleted +
                ", members=" + members +
                ", messages=" + messages +
                '}';
    }
}


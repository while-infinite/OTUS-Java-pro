package org.otus.javapro.filter;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class ChatSessionFilter implements Serializable {
    @SerializedName(value = "chat_identifier")
    private String chatIdentifier;

    private List<String> last;

    private Map<String, List<MessageFilter>> messages;

    @Override
    public String toString() {
        return "{" +
                "chatIdentifier='" + chatIdentifier + '\'' +
                ", last=" + last +
                ", messages=" + messages +
                '}';
    }
}

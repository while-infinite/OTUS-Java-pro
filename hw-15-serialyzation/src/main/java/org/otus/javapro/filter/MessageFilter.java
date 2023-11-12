package org.otus.javapro.filter;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
@Setter
@Getter
@Builder
public class MessageFilter implements Serializable {
    @SerializedName(value = "send_date")
    private LocalDateTime sendDate;

    @SerializedName(value = "belong_number")
    private String belongNumber;

    private String text;

    @Override
    public String toString() {
        return "{" +
                "sendDate=" + sendDate +
                ", belongNumber=" + belongNumber +
                ", text=" + text +
                '}';
    }
}

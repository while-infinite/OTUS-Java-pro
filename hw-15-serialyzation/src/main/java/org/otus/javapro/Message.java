package org.otus.javapro;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Message implements Serializable {

    @SerializedName(value = "ROWID")
    private Long rowId;
    @SerializedName(value = "attributedBody")
    private String attributedBody;
    @SerializedName(value = "belong_number")
    private String belongNumber;
    private BigDecimal date;
    @SerializedName(value = "date_read")
    private BigDecimal dateRead;
    private UUID guid;
    @SerializedName(value = "handle_id")
    private Long handleId;
    @SerializedName(value = "has_dd_results")
    private Boolean hasDdResults;
    @SerializedName(value = "is_deleted")
    private Boolean isDeleted;
    @SerializedName(value = "is_from_me")
    private Boolean isFromMe;
    @SerializedName(value = "send_date")
    private LocalDateTime sendDate;
    @SerializedName(value = "send_status")
    private int sendStatus;
    private String service;
    private String text;

    @Override
    public String toString() {
        return "Message{" +
                "rowId=" + rowId +
                ", attributedBody='" + attributedBody + '\'' +
                ", belongNumber='" + belongNumber + '\'' +
                ", date=" + date +
                ", dateRead=" + dateRead +
                ", guid=" + guid +
                ", handleId=" + handleId +
                ", hasDdResults=" + hasDdResults +
                ", isDeleted=" + isDeleted +
                ", isFromMe=" + isFromMe +
                ", sendDate=" + sendDate +
                ", sendStatus=" + sendStatus +
                ", service='" + service + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}


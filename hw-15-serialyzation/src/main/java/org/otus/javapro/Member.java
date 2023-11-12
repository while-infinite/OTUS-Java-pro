package org.otus.javapro;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Member implements Serializable {

    private String first;
    @SerializedName(value = "handle_id")
    private Long handleId;
    @SerializedName(value = "image_path")
    private String imagePath;
    private String last;
    private String middle;
    @SerializedName(value = "phone_number")
    private String phoneNumber;
    private String service;
    @SerializedName(value = "thumb_path")
    private String thumbPath;

    @Override
    public String toString() {
        return "Member{" +
                "first='" + first + '\'' +
                ", handleId=" + handleId +
                ", imagePath='" + imagePath + '\'' +
                ", last='" + last + '\'' +
                ", middle='" + middle + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", service='" + service + '\'' +
                ", thumbPath='" + thumbPath + '\'' +
                '}';
    }

}


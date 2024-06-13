package me.ryzeon.domininghub.entity;

import lombok.Data;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/6/24 @ 18:50
 */
@Data
public class UserInfo {

    private String position;
    private String company;
    private String about;

    public UserInfo() {
        this.position = "";
        this.company = "";
        this.about = "";
    }

    public UserInfo(String position, String company, String about) {
        this.position = position;
        this.company = company;
        this.about = about;
    }
}

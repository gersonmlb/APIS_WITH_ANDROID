package com.kaluzny.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Document(collection = "Users")
public class Users {

    @Id
    @Field("id")
    private int id;

    @NotNull
    @Size(min = 1)
    @Field("name")
    private String name;

    @NotNull
    @Size(min = 1)
    @Field("nickname")
    private String nickname;

    public Users(int id, String name, String nickname) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
    }
}
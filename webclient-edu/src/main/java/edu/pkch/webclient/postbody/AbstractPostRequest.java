package edu.pkch.webclient.postbody;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
public abstract class AbstractPostRequest {
    private String id;

    public AbstractPostRequest(String id) {
        this.id = id;
    }
}

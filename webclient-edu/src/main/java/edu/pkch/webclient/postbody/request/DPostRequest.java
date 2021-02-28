package edu.pkch.webclient.postbody.request;

import edu.pkch.webclient.postbody.AbstractPostRequest;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
public class DPostRequest extends AbstractPostRequest {
    private String firstName;
    private String lastName;

    @Builder
    public DPostRequest(String id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

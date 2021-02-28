package edu.pkch.webclient.postbody.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class D2PostRequest {
    private String firstName;
    private String lastName;
    private String id;

    @Builder
    public D2PostRequest(String firstName, String lastName, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }
}

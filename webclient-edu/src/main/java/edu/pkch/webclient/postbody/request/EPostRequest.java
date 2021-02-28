package edu.pkch.webclient.postbody.request;

import edu.pkch.webclient.postbody.AbstractPostRequest;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class EPostRequest {
    private String firstName;
    private String lastName;
    private AbstractPostRequest abstractPostRequest;

    @Builder
    public EPostRequest(String firstName, String lastName, AbstractPostRequest abstractPostRequest) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.abstractPostRequest = abstractPostRequest;
    }
}

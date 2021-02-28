package edu.pkch.webclient.postbody.request;

import edu.pkch.webclient.postbody.TestRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CPostRequest {
    private String firstName;
    private String lastName;
    private TestRequest testRequest;

    @Builder
    public CPostRequest(String firstName, String lastName, TestRequest testRequest) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.testRequest = testRequest;
    }
}

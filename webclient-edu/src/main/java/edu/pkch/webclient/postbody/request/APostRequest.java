package edu.pkch.webclient.postbody.request;


import edu.pkch.webclient.postbody.PostRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class APostRequest implements PostRequest {

    private String firstName;
    private String lastName;

    @Builder
    public APostRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

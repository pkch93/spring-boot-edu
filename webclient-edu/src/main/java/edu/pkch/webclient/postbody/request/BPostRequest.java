package edu.pkch.webclient.postbody.request;


import edu.pkch.webclient.postbody.PostRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BPostRequest<T> implements PostRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private T t;

    @Builder
    public BPostRequest(String firstName, String middleName, String lastName, T t) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.t = t;
    }
}

package edu.pkch.webclient.postbody.request;

import edu.pkch.webclient.postbody.TestRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestRequestImpl implements TestRequest {
    private String test;

    @Builder
    public TestRequestImpl(String test) {
        this.test = test;
    }
}

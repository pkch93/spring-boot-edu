package edu.pkch.webclient.postbody.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FPostRequest {
    private List<EPostRequest> ePostRequests;

    @Builder
    public FPostRequest(List<EPostRequest> ePostRequests) {
        this.ePostRequests = ePostRequests;
    }
}

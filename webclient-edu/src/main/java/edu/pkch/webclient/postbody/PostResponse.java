package edu.pkch.webclient.postbody;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostResponse {

    private boolean success;

    @Builder
    public PostResponse(boolean success) {
        this.success = success;
    }
}

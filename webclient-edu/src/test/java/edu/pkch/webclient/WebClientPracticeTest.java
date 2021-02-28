package edu.pkch.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pkch.webclient.postbody.PostRequest;
import edu.pkch.webclient.postbody.PostResponse;
import edu.pkch.webclient.postbody.TestRequest;
import edu.pkch.webclient.postbody.request.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.UnsupportedMediaTypeException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * WebClientPracticeApplication을 실행시킨 후 테스트 해야함..!
 * @see WebClientPracticeApplication
 */
@SpringBootTest
class WebClientPracticeTest {
    private static final String BASE_URL = "/v1/post-requests";

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private ObjectMapper objectMapper;

    private WebClient webClient;

    @BeforeEach
    void setUp() {
        webClient = webClientBuilder.uriBuilderFactory(new DefaultUriBuilderFactory(
                UriComponentsBuilder.newInstance()
                        .scheme("http")
                        .host("localhost")
                        .port("8080")
        ))
                .build();
    }

    @Test
    @DisplayName("인터페이스 타입으로 post 요청시 body를 제공하는 경우 UnsupportedMediaTypeException 발생")
    void test_bodyToInterface() {
        APostRequest aPostRequest = APostRequest.builder()
                .firstName("firstName")
                .lastName("lastName")
                .build();

        Mono<PostResponse> actual = webClient.post()
                .uri(BASE_URL + "/interface")
                .body(Mono.just(aPostRequest), PostRequest.class)
                .retrieve()
                .bodyToMono(PostResponse.class);

        StepVerifier.create(actual)
                .expectError(UnsupportedMediaTypeException.class)
                .verify();
    }

    @Test
    @DisplayName("클래스 타입으로 post 요청시 body를 제공하는 경우 정상 요청")
    void test_bodyToClassType() {
        APostRequest aPostRequest = APostRequest.builder()
                .firstName("firstName")
                .lastName("lastName")
                .build();

        Mono<PostResponse> actual = webClient.post()
                .uri(BASE_URL + "/a")
                .body(Mono.just(aPostRequest), APostRequest.class)
                .retrieve()
                .bodyToMono(PostResponse.class);

        StepVerifier.create(actual)
                .consumeNextWith(postResponse -> {
                    assertTrue(postResponse.isSuccess());
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("[클래스 타입 내부에 제네릭 변수가 있을 때] post 요청시 body를 제공하는 경우 정상 요청")
    void test_bodyToClassTypeWithGeneric() {
        BPostRequest<String> bPostRequest = BPostRequest.<String>builder()
                .firstName("firstName")
                .middleName("middleName")
                .lastName("lastName")
                .t("t")
                .build();

        Mono<PostResponse> actual = webClient.post()
                .uri(BASE_URL + "/b")
                .body(Mono.just(bPostRequest), BPostRequest.class)
                .retrieve()
                .bodyToMono(PostResponse.class);

        StepVerifier.create(actual)
                .consumeNextWith(postResponse -> {
                    assertTrue(postResponse.isSuccess());
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("[클래스 타입 내부에 제네릭 인터페이스가 있을 때] post 요청시 body를 제공하는 경우 에러 발")
    void test_bodyToClassTypeWithGenericInterface() {
        BPostRequest<TestRequest> bPostRequest = BPostRequest.<TestRequest>builder()
                .firstName("firstName")
                .middleName("middleName")
                .lastName("lastName")
                .t(TestRequestImpl.builder()
                        .test("test")
                        .build()
                )
                .build();

        Mono<PostResponse> actual = webClient.post()
                .uri(BASE_URL + "/b")
                .body(Mono.just(bPostRequest), BPostRequest.class)
                .retrieve()
                .bodyToMono(PostResponse.class);

        StepVerifier.create(actual)
                .consumeNextWith(postResponse -> {
                    assertFalse(postResponse.isSuccess());
                })
                .verifyComplete();
    }

    /**
     * Type definition error: [simple type, class edu.pkch.webclient.postbody.TestRequest]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException:
     * Cannot construct instance of `edu.pkch.webclient.postbody.TestRequest` (no Creators, like default construct, exist):
     * abstract types either need to be mapped to concrete types, have custom deserializer, or contain additional type information
     * at [Source: (io.netty.buffer.ByteBufInputStream); line: 1, column: 62] (through reference chain: edu.pkch.webclient.postbody.request.CPostRequest["testRequest"])
     */
    @Test
    @DisplayName("[클래스 타입 내부에 인터페이스 변수가 있을 때] post 요청시 body를 제공하는 경우 에러 발생")
    void test_bodyToClassTypeWithInterface() {
        CPostRequest cPostRequest = CPostRequest.builder()
                .firstName("firstName")
                .lastName("lastName")
                .testRequest(TestRequestImpl.builder()
                        .test("test")
                        .build()
                )
                .build();

        Mono<PostResponse> actual = webClient.post()
                .uri(BASE_URL + "/c")
                .body(Mono.just(cPostRequest), CPostRequest.class)
                .retrieve()
                .bodyToMono(PostResponse.class);

        StepVerifier.create(actual)
                .consumeNextWith(postResponse -> {
                    assertFalse(postResponse.isSuccess());
                })
                .verifyComplete();
    }

    /**
     * error: Type definition error: [simple type, class edu.pkch.webclient.postbody.request.DPostRequest];
     * nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException:
     * Cannot construct instance of `edu.pkch.webclient.postbody.request.DPostRequest` (no Creators, like default construct, exist):
     * cannot deserialize from Object value (no delegate- or property-based Creator)
     *  at [Source: (io.netty.buffer.ByteBufInputStream); line: 1, column: 2]
     */
    @Test
    @DisplayName("[추상클래스를 상속받은 클래스 타입] post 요청시 body를 제공하는 경우 에러 발생")
    void test_bodyToClassTypeExtendedAbstractClass() {
        DPostRequest dPostRequest = DPostRequest.builder()
                .firstName("firstName")
                .lastName("lastName")
                .id("pkch")
                .build();

        Mono<PostResponse> actual = webClient.post()
                .uri(BASE_URL + "/d")
                .body(Mono.just(dPostRequest), DPostRequest.class)
                .retrieve()
                .bodyToMono(PostResponse.class);

        StepVerifier.create(actual)
                .consumeNextWith(postResponse -> {
                    assertFalse(postResponse.isSuccess());
                })
                .verifyComplete();
    }

    /**
     * Jackson이 클라이언트에서 매핑할 때는 인터페이스에 구현체가 매핑되어 있으므로 정상적으로 json으로 컨버팅이 가능함
     * 구현체에 getter가 붙어 있으므로 접근 가능
     *
     * 단, 서버 측에서는 제공한 json을 인터페이스/추상클래스로 받는 경우 어떤 객체로 컨버팅해야할 지 알 수 없음.
     * 이런 이유로 에러가 발생.
     */
    @Test
    @DisplayName("[추상클래스를 상속받은 클래스 타입 - 서버는 Abstract로 받지 않음] post 요청시 body를 제공하는 경우 정상 요청")
    void test_bodyToClassTypeExtendedAbstractClass_serverReceiveD2PostRequest() {
        DPostRequest dPostRequest = DPostRequest.builder()
                .firstName("firstName")
                .lastName("lastName")
                .id("pkch")
                .build();

        Mono<PostResponse> actual = webClient.post()
                .uri(BASE_URL + "/d2")
                .body(Mono.just(dPostRequest), DPostRequest.class)
                .retrieve()
                .bodyToMono(PostResponse.class);

        StepVerifier.create(actual)
                .consumeNextWith(postResponse -> {
                    assertTrue(postResponse.isSuccess());
                })
                .verifyComplete();
    }



    @Test
    @DisplayName("[추상클래스를 필드로 가진 클래스 타입] post 요청시 body를 제공하는 경우 에러 발생")
    void test_bodyToClassTypeHasAbstractClass() throws JsonProcessingException {
        EPostRequest ePostRequest = EPostRequest.builder()
                .firstName("firstName")
                .lastName("lastName")
                .abstractPostRequest(DPostRequest.builder()
                        .firstName("firstName")
                        .lastName("lastName")
                        .id("pkch")
                        .build()
                )
                .build();

        Mono<PostResponse> actual = webClient.post()
                .uri(BASE_URL + "/e")
                .body(Mono.just(ePostRequest), EPostRequest.class)
                .retrieve()
                .bodyToMono(PostResponse.class);

        StepVerifier.create(actual)
                .consumeNextWith(postResponse -> {
                    assertFalse(postResponse.isSuccess());
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("[추상클래스를 담은 리스트를 필드로 가진 클래스 타입] post 요청시 body를 제공하는 경우 정상 응답")
    void test_bodyToClassTypeHasAbstractClassList() throws JsonProcessingException {
        FPostRequest fPostRequest = FPostRequest.builder()
                .ePostRequests(Arrays.asList(
                        EPostRequest.builder()
                                .firstName("firstName")
                                .lastName("lastName")
                                .abstractPostRequest(DPostRequest.builder()
                                        .firstName("firstName")
                                        .lastName("lastName")
                                        .id("pkch")
                                        .build()
                                )
                                .build()
                ))
                .build();

        System.out.println(objectMapper.writeValueAsString(fPostRequest));

        Mono<PostResponse> actual = webClient.post()
                .uri(BASE_URL + "/f")
                .body(Mono.just(fPostRequest), FPostRequest.class)
                .retrieve()
                .bodyToMono(PostResponse.class);

        StepVerifier.create(actual)
                .consumeNextWith(postResponse -> {
                    assertFalse(postResponse.isSuccess());
                })
                .verifyComplete();
    }
}
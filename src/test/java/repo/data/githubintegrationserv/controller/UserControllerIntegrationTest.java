package repo.data.githubintegrationserv.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;
import repo.data.githubintegrationserv.api.domain.response.User;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIntegrationTest {

    @Autowired
    private RestClient restClient;

    @Autowired
    private ObjectMapper objectMapper;

    private String readJsonFile(String fileName) throws Exception {
        return Files.readString(Path.of("src/test/resources/json/" + fileName));
    }

    String baseUrl = "http://localhost:8080";
    String uri = "/api/v1/user/{userName}";

    @Test
    void testGetUserRepositoryDataWithValidUsernameHappyPath() throws Exception {
        String validUsername = "octocat";

        User response = restClient
                .get()
                .uri(baseUrl + uri, validUsername)
                .retrieve()
                .body(User.class);

        assertThat(response).isNotNull();

        // Convert response to JSON string
        String actualJson = objectMapper.writeValueAsString(response);

        // Read expected JSON from file
        String expectedJson = readJsonFile("user_valid.json");

        // Assert actual and expected JSON response
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    @Test
    void testGetUserRepositoryDataWithInvalidUsername() throws Exception {
        String invalidUsername = "invalid@user";

        try {
            restClient
                    .get()
                    .uri(baseUrl + uri, invalidUsername)
                    .retrieve()
                    .body(User.class);

            // If an exception is not thrown, fail the test
            fail("Expected HttpClientErrorException to be thrown");
        } catch (HttpClientErrorException ex) {
            assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            String actualJson = ex.getResponseBodyAsString();
            String expectedJson = readJsonFile("user_invalid.json");
            JSONAssert.assertEquals(expectedJson, actualJson, false);
        }
    }

    @Test
    void testGetUserRepositoryData_UserNotFound_ReturnsNotFoundJson() throws Exception {
        String unknownUsername = "a-b-c-d-e-user";
        try {
            restClient
                    .get()
                    .uri(baseUrl + uri, unknownUsername)
                    .retrieve()
                    .body(User.class);

            // If no exception is thrown, fail the test
            fail("Expected HttpServerErrorException to be thrown");
        } catch (HttpServerErrorException ex) {
            assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
            String actualJson = ex.getResponseBodyAsString();
            String expectedJson = readJsonFile("user_not_found.json");
            JSONAssert.assertEquals(expectedJson, actualJson, false);
        }
    }
}

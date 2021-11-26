package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.authentication.AuthenticationResult;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class postRequest {

    public static AuthenticationResult call_me(String username, String password) throws Exception {
        AuthenticationResult authenticationResult = new AuthenticationResult();

        var values = new HashMap<String, String>() {{
            put("userName", username);
            put("userPassword", password);
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9091/authenticate"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("content-type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        try {
            JSONObject myResponse = new JSONObject(response.body());
            System.out.println(myResponse.getString("accessToken"));

            authenticationResult.setSuccessful(true);
            authenticationResult.setAccessToken(myResponse.getString("accessToken"));
            authenticationResult.setRole(myResponse.getString("role"));
            //System.out.println(authenticationResult.getRole().contains("User"));

        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return authenticationResult;

    }
}

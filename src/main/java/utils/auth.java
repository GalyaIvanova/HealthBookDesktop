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

public class auth {

        public static AuthenticationResult call_auth(String accessToken) throws Exception {
            AuthenticationResult authenticationResult = new AuthenticationResult();

            var values = new HashMap<String, String>() {{}};

            var objectMapper = new ObjectMapper();
            String requestBody = objectMapper
                    .writeValueAsString(values);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:9091/forAdmin"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("content-type", "application/json")
                    .header("Authorization", "Bearer "+accessToken)
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            try {
                JSONObject myResponse = new JSONObject(response.body());
                System.out.println(myResponse.getString("roleName"));
                authenticationResult.setRole(myResponse.getString("roleName"));
                authenticationResult.setSuccessful(true);

            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            return authenticationResult;

        }
    }



package org.twitchapis.jta.twitch.api.actions;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author LoboMetalurgico
 * @since 2021-02-21
 */

public class TokenVerify {
    public static HttpResponse<String> get(String url, String token) throws URISyntaxException, IOException, InterruptedException {
        String finalToken;

        if (token.startsWith("oauth:")) {
            finalToken = token.replace("oauth: ", "OAuth ");
            finalToken = finalToken.replace("oauth:", "OAuth ");
        } else {
            finalToken = "OAuth " + token;
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .uri(URI.create(url))
                .headers("User-Agent", "JavaTwitchAPI/1.0.0", "Authorization", finalToken)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();
        int responseStatusCode = response.statusCode();

        System.out.println("httpGetRequest: " + responseBody);
        System.out.println("httpGetRequest status code: " + responseStatusCode);

        return response;
    }
}

package api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GroqAPIClient {

    // Replace these with the actual API URL and API Key
    private static final String API_URL = "https://api.groq.com/openai/v1/chat/completions";
    private static final String API_KEY = "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"; // Replace with your actual API key

    public static String sendMessage(String prompt) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Create HTTP POST request
            HttpPost postRequest = new HttpPost(API_URL);

            // Add headers
            postRequest.setHeader("Authorization", "Bearer " + API_KEY);
            postRequest.setHeader("Content-Type", "application/json");

            // Create JSON payload
            JsonObject payload = new JsonObject();
            payload.addProperty("model", "llama-3.1-8b-instant"); // Replace with the correct model name from Groq API docs

            // Add messages array with the user's message
            JsonObject userMessage = new JsonObject();
            userMessage.addProperty("role", "user");
            userMessage.addProperty("content", prompt);

            JsonArray messages = new JsonArray();
            messages.add(userMessage);

            payload.add("messages", messages);
            payload.addProperty("max_tokens", 150);

            System.out.println("Payload: " + payload.toString());

            // Attach payload to the request
            StringEntity entity = new StringEntity(payload.toString());
            postRequest.setEntity(entity);

            // Execute the request
            try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                // Check response status
                int statusCode = response.getStatusLine().getStatusCode();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent())
                );
                StringBuilder responseContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }

                System.out.println("Response Body: " + responseContent.toString());

                if (statusCode == 200) {
                    return responseContent.toString();
                } else {
                    return "Error: Received status code " + statusCode + "\nResponse Body: " + responseContent;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

//    public static void main(String[] args) {
//        String prompt = "Hello, how can I assist you today?";
//        String response = sendMessage(prompt);
//        System.out.println("Groq API Response: " + response);
//    }
}

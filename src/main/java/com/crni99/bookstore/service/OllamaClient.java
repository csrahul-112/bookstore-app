package com.crni99.bookstore.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class OllamaClient {

    private static final Logger logger = LoggerFactory.getLogger(OllamaClient.class);

    private final RestTemplate restTemplate;

    @Value("${ollama.api.url:http://localhost:11434/api/generate}")
    private String ollamaApiUrl;

    @Value("${ollama.model.name:llama3.2}")
    private String ollamaModelName;

    public OllamaClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateResponse(String userPrompt) {
        OllamaGenerateRequest ollamaRequest = new OllamaGenerateRequest(ollamaModelName, userPrompt, false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OllamaGenerateRequest> entity = new HttpEntity<>(ollamaRequest, headers);

        try {
            logger.info("Sending request to Ollama: model={}, prompt='{}'", ollamaRequest.getModel(), ollamaRequest.getPrompt());
            ResponseEntity<OllamaGenerateResponse> responseEntity = restTemplate.postForEntity(
                    ollamaApiUrl,
                    entity,
                    OllamaGenerateResponse.class);

            if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
                logger.info("Received response from Ollama: '{}'", responseEntity.getBody().getResponse());
                return responseEntity.getBody().getResponse();
            } else {
                logger.error("Ollama API call failed or returned an empty response. Status: {}, Body: {}",
                        responseEntity.getStatusCode(), responseEntity.getBody());
                return "Sorry, I couldn't get a response from the local AI model.";
            }
        } catch (HttpClientErrorException e) {
            logger.error("HttpClientErrorException during Ollama API call: {} - {}", e.getStatusCode(), e.getResponseBodyAsString(), e);
            return "Sorry, there was an error communicating with the local AI model: " + e.getStatusCode();
        } catch (Exception e) {
            logger.error("Exception during Ollama API call: {}", e.getMessage(), e);
            return "Sorry, an unexpected error occurred while contacting the local AI model.";
        }
    }

    // --- Inner DTOs for Ollama API ---
    private static class OllamaGenerateRequest {
        private String model;
        private String prompt;
        private Boolean stream;
        // Add other parameters like "options", "system", "template", "context" if needed

        public OllamaGenerateRequest(String model, String prompt, Boolean stream) {
            this.model = model;
            this.prompt = prompt;
            this.stream = stream;
        }

        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
        public String getPrompt() { return prompt; }
        public void setPrompt(String prompt) { this.prompt = prompt; }
        public Boolean getStream() { return stream; }
        public void setStream(Boolean stream) { this.stream = stream; }
    }

    // Based on Ollama's /api/generate response structure when stream=false
    private static class OllamaGenerateResponse {
        private String model;
        private @JsonProperty("created_at") String createdAt;
        private String response; // This is the main generated text
        private Boolean done;

        // Fields available when done=true
        @JsonProperty("total_duration")
        private Long totalDuration;
        @JsonProperty("load_duration")
        private Long loadDuration;
        @JsonProperty("prompt_eval_count")
        private Integer promptEvalCount;
        @JsonProperty("prompt_eval_duration")
        private Long promptEvalDuration;
        @JsonProperty("eval_count")
        private Integer evalCount;
        @JsonProperty("eval_duration")
        private Long evalDuration;
        private List<Integer> context; // Only if "keep_alive" is not set to 0 and not the last request

        // Getters and Setters
        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
        public String getCreatedAt() { return createdAt; }
        public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
        public String getResponse() { return response; }
        public void setResponse(String response) { this.response = response; }
        public Boolean getDone() { return done; }
        public void setDone(Boolean done) { this.done = done; }
        public Long getTotalDuration() { return totalDuration; }
        public void setTotalDuration(Long totalDuration) { this.totalDuration = totalDuration; }
        public Long getLoadDuration() { return loadDuration; }
        public void setLoadDuration(Long loadDuration) { this.loadDuration = loadDuration; }
        public Integer getPromptEvalCount() { return promptEvalCount; }
        public void setPromptEvalCount(Integer promptEvalCount) { this.promptEvalCount = promptEvalCount; }
        public Long getPromptEvalDuration() { return promptEvalDuration; }
        public void setPromptEvalDuration(Long promptEvalDuration) { this.promptEvalDuration = promptEvalDuration; }
        public Integer getEvalCount() { return evalCount; }
        public void setEvalCount(Integer evalCount) { this.evalCount = evalCount; }
        public Long getEvalDuration() { return evalDuration; }
        public void setEvalDuration(Long evalDuration) { this.evalDuration = evalDuration; }
        public List<Integer> getContext() { return context; }
        public void setContext(List<Integer> context) { this.context = context; }
    }
}

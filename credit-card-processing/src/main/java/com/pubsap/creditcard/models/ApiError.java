package com.pubsap.creditcard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"timestamp",  "status", "error", "message", "path"})
public class ApiError {

    @JsonProperty("timestamp")
    private Timestamp timestamp;
    @JsonProperty("status")
    private int status;
    @JsonProperty("error")
    private HttpStatus error;
    @JsonProperty("message")
    private String message;
    @JsonProperty("path")
    private String path;

    public ApiError(Timestamp timestamp, int status, HttpStatus error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}

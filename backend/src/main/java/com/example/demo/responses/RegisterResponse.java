package com.example.demo.responses;

import com.example.demo.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("user")
    private UserEntity user;
}

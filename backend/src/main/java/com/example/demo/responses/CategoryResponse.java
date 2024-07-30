package com.example.demo.responses;


import com.example.demo.entity.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("errors")
    private List<String> errors;

    @JsonProperty("category")
    private CategoryEntity category;
}


package com.senai.devagro.devagro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StandardErrorDTO {

    private Instant instantError;
    private String error;
    private String message;
    private String path;
}

package com.logicaemocional.api2.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmotionalEntryRequest {

    @NotBlank(message = "El contenido emocional es obligatorio")
    @Size(min = 10, max = 2000, message = "El contenido debe tener entre 10 y 2000 caracteres")
    private String content;
}
package com.sicred.takehomesicred.app.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record NovaPautaEdition(String tituloPauta, String descricaoPauta, Integer duracaoSessao, String createdBy) {}

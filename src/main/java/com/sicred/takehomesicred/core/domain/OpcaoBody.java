package com.sicred.takehomesicred.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OpcaoBody(Object dadosOpcao) {}

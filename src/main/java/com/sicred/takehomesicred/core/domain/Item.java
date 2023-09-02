package com.sicred.takehomesicred.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Item(String id, String texto, ItemType tipo, String titulo, String url, OpcaoBody body, Object valor) {}

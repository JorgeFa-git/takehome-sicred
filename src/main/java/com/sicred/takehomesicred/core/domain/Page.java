package com.sicred.takehomesicred.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Page(String titulo, PageType tipo, List<Item> itens, Button botaoOk, Button botaoCancelar) {}

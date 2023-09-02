package com.sicred.takehomesicred.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Button(String texto, String url, ButtonBody body) {}

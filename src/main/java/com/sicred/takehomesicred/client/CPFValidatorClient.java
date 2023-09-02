package com.sicred.takehomesicred.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "cpf-validator",
        url = "${cpf-validator.url}"
)
public interface CPFValidatorClient {

//    @GetMapping("/users/{cpf}")
//    public ResponseEntity<> validate(@PathVariable("cpf") String cpf);
}

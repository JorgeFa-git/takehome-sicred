package com.sicred.takehomesicred.app;

import com.sicred.takehomesicred.app.dto.NovaPautaEdition;
import com.sicred.takehomesicred.core.domain.Button;
import com.sicred.takehomesicred.core.domain.OpcaoBody;
import com.sicred.takehomesicred.core.domain.Page;
import com.sicred.takehomesicred.core.domain.PageType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppResource {

    @Value("${app.dominio}")
    private String dominio;

    private final AppService appService;

    public AppResource(AppService appService) {
        this.appService = appService;
    }

    @PostMapping("/")
    public ResponseEntity<Page> homePage() {
        return ResponseEntity.ok(appService.getHomePage());
    }

    @PostMapping("/pauta/nova-pauta")
    public ResponseEntity<Page> novaPauta(@RequestBody OpcaoBody opcaoBody) {
        return ResponseEntity.ok(appService.getNovaPautaPage(opcaoBody));
    }

    @PostMapping("pauta/salvar-pauta")
    public ResponseEntity<Page> salvarPauta(@RequestBody NovaPautaEdition novaPautaEdition) {
        appService.saveNovaPauta(novaPautaEdition);
        return ResponseEntity.ok(new Page("Pauta criada com sucesso!", PageType.FORMULARIO, null, null, new Button("Inicio", dominio, null)));
    }

    @PostMapping("/pauta/listar-pautas")
    public ResponseEntity<Page> listarPautas() {
        return ResponseEntity.ok(appService.getPautaList());
    }

    @PostMapping("/pauta/{id}")
    public ResponseEntity<Page> pauta(@PathVariable Long id) {
        return ResponseEntity.ok(appService.getPauta(id));
    }

    @PostMapping("/pauta/{id}/confirmar-voto")
    public ResponseEntity<Page> confirmVote(@RequestBody OpcaoBody opcaoBody, @PathVariable Long id) {
        return ResponseEntity.ok(appService.getConfirmVotePage((boolean) opcaoBody.dadosOpcao(), id));
    }
}

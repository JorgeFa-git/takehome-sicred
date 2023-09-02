package com.sicred.takehomesicred.app;

import com.sicred.takehomesicred.app.dto.NovaPautaEdition;
import com.sicred.takehomesicred.app.dto.Pauta;
import com.sicred.takehomesicred.app.dto.PautaStatus;
import com.sicred.takehomesicred.app.dto.Vote;
import com.sicred.takehomesicred.core.domain.Button;
import com.sicred.takehomesicred.core.domain.ButtonBody;
import com.sicred.takehomesicred.core.domain.Item;
import com.sicred.takehomesicred.core.domain.ItemType;
import com.sicred.takehomesicred.core.domain.OpcaoBody;
import com.sicred.takehomesicred.core.domain.Page;
import com.sicred.takehomesicred.core.domain.PageType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AppService {

    @Value("${app.dominio}")
    private String dominio;

    private final PautaRepository pautaRepository;

    private final VoteRepository voteRepository;

    public AppService(PautaRepository pautaRepository, VoteRepository voteRepository) {
        this.pautaRepository = pautaRepository;
        this.voteRepository = voteRepository;
    }

//    private final CPFValidatorClient cpfValidatorClient;

//    public AppService(CPFValidatorClient cpfValidatorClient) {
//        this.cpfValidatorClient = cpfValidatorClient;
//    }

    public Page getHomePage() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(null, "Nova Pauta", null, null, dominio + "/pauta/nova-pauta", new OpcaoBody("12345678912"), null));
        items.add(new Item(null, "Todas as pautas disponíveis", null, null, dominio + "/pauta/listar-pautas", new OpcaoBody("12345678912"), null));

        return new Page("O que deseja fazer?", PageType.SELECAO, items, null, null);
    }

    public Page getNovaPautaPage(OpcaoBody opcaoBody) {
//        this.cpfValidatorClient.validate(opcaoBody);

        List<Item> items = new ArrayList<>();
        items.add(new Item("tituloPauta", null, ItemType.INPUT_TEXTO, "Digite o título da nova pauta", null, null, ""));
        items.add(new Item("descricaoPauta", null, ItemType.INPUT_TEXTO, "Digite a descrição da nova pauta", null, null, ""));
        items.add(new Item("duracaoSessao", null, ItemType.INPUT_NUMERO, "Digite o tempo a duração da sessão minutos", null, null, 1));

        return new Page("Nova Pauta", PageType.FORMULARIO, items, new Button("Confirmar", dominio + "/pauta/salvar-pauta", new ButtonBody("12345678912")), new Button("Cancelar", dominio, null));
    }

    public void saveNovaPauta(NovaPautaEdition novaPautaEditiion) {
        Pauta newPauta = new Pauta(
                null,
                novaPautaEditiion.tituloPauta(),
                novaPautaEditiion.descricaoPauta(),
                OffsetDateTime.now().plusMinutes(novaPautaEditiion.duracaoSessao()),
                PautaStatus.ABERTA,
                novaPautaEditiion.createdBy()
        );
        this.pautaRepository.save(newPauta);
    }

    public Page getPautaList() {
        List<Pauta> pautas = this.pautaRepository.findAll();
        List<Item> items = new ArrayList<>();
        pautas.forEach(pauta -> {
            items.add(new Item(null, pauta.getTituloPauta(), null, null, dominio + "/pauta/" + pauta.getId(), null, null));
        });

        return new Page("Lista de Pautas", PageType.SELECAO, items, null, null);
    }

    public Page getPauta(Long id) {
        Optional<Pauta> pauta = this.pautaRepository.findById(id);

        return pauta.map(value -> new Page(value.getTituloPauta(), PageType.SELECAO, Arrays.asList(
                new Item(null, pauta.get().getDescricaoPauta(), ItemType.TEXTO, null, null, null, null),
                new Item(null, "Sim", null, null, dominio + "/pauta/" + pauta.get().getId() + "/confirmar-voto", new OpcaoBody(true), null),
                new Item(null, "Não", null, null, dominio + "/pauta/" + pauta.get().getId() + "/confirmar-voto", new OpcaoBody(false), null)
        ), null, null)).orElseGet(() -> new Page("Pauta não encontrada", PageType.FORMULARIO, null, null, new Button("Voltar", dominio + "/pauta/listar-pautas", null)));
    }

    public Page getConfirmVotePage(boolean vote, Long pautaId) {
        Optional<Pauta> pauta = this.pautaRepository.findById(pautaId);

        if (pauta.isEmpty()) {
            return new Page("Pauta não encontrada", PageType.FORMULARIO, null, null, new Button("Voltar", dominio + "/pauta/listar-pautas", null));
        }

        if (pauta.get().getExpiresAt().isBefore(OffsetDateTime.now())) {
            return new Page("A sessão da pauta foi fechada", PageType.FORMULARIO, null, null, new Button("Voltar", dominio + "/pauta/listar-pautas", null));
        }

        this.voteRepository.save(new Vote(null, vote, pautaId));

        return new Page("Voto confirmado", PageType.FORMULARIO, Collections.singletonList(
                new Item(null, "Voto confirmado", ItemType.TEXTO, null, null, null, null)
        ), null, new Button("Voltar", dominio, null));
    }
}

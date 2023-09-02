package com.sicred.takehomesicred.app.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;

@Entity
@Table(name = "pauta")
public class Pauta {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private String tituloPauta;
    @Column()
    private String descricaoPauta;
    @Column()
    private OffsetDateTime expiresAt;
    @Column()
    private PautaStatus status;
    @Column()
    private String createdBy;

    public Pauta() {
    }

    public Pauta(Long id, String tituloPauta, String descricaoPauta, OffsetDateTime expiresAt, PautaStatus status, String createdBy) {
        this.id = id;
        this.tituloPauta = tituloPauta;
        this.descricaoPauta = descricaoPauta;
        this.expiresAt = expiresAt;
        this.status = status;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloPauta() {
        return tituloPauta;
    }

    public void setTituloPauta(String tituloPauta) {
        this.tituloPauta = tituloPauta;
    }

    public String getDescricaoPauta() {
        return descricaoPauta;
    }

    public void setDescricaoPauta(String descricaoPauta) {
        this.descricaoPauta = descricaoPauta;
    }

    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public PautaStatus getStatus() {
        return status;
    }

    public void setStatus(PautaStatus status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}

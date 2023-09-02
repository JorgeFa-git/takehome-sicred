package com.sicred.takehomesicred.app;

import com.sicred.takehomesicred.app.dto.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
}


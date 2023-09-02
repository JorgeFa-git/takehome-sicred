package com.sicred.takehomesicred.app;

import com.sicred.takehomesicred.app.dto.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}

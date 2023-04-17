package com.fssm.chargehoraire.Repositories;

import com.fssm.chargehoraire.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findByName(String name);
}

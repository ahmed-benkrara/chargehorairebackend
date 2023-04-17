package com.fssm.chargehoraire.Repositories;

import com.fssm.chargehoraire.Models.TypeH;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeHRepository extends JpaRepository<TypeH, Long> {
    TypeH findByName(String name);
}

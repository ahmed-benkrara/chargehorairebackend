package com.fssm.chargehoraire.Repositories;

import com.fssm.chargehoraire.Models.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester, Long> {
}

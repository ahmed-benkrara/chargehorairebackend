package com.fssm.chargehoraire.Repositories;

import com.fssm.chargehoraire.Models.Teaches;
import com.fssm.chargehoraire.Models.TeachesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeachesRepository extends JpaRepository<Teaches, TeachesKey> {
    @Query("select t from Teaches t where t.key.teacher_id = :teacher and t.key.year.name = :year group by t.key.module_id")
    List<Teaches> getTeachesForType(@Param("teacher") long teacher_id, @Param("year") String year);
}

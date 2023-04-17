package com.fssm.chargehoraire.Repositories;

import com.fssm.chargehoraire.Models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByCin(String cin);
    Teacher findByEmail(String email);

    void deleteById(Long id);

//    @Modifying
//    @Query("DELETE FROM Teacher t where t.id = :ID")
//    void rmTeacher(@Param("ID") Long id);
}

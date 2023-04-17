package com.fssm.chargehoraire.Repositories;

import com.fssm.chargehoraire.Models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);

//    @Query(value = "DELIMITER $$\n" +
//            "CREATE TRIGGER before_department_delete\n" +
//            "BEFORE DELETE ON Department\n" +
//            "FOR EACH ROW\n" +
//            "BEGIN\n" +
//            "  DELETE FROM Field WHERE department_id = OLD.id;\n" +
//            "  DELETE FROM AdminTask WHERE department_id = OLD.id;\n" +
//            "END$$\n" +
//            "DELIMITER ;",
//            nativeQuery = true)
//    void createDepartmentBeforeDeleteTrigger();
}

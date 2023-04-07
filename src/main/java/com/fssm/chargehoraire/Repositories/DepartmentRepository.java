package com.fssm.chargehoraire.Repositories;

import com.fssm.chargehoraire.Models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

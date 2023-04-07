package com.fssm.chargehoraire.Repositories;

import com.fssm.chargehoraire.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}

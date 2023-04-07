package com.fssm.chargehoraire.Repositories;

import com.fssm.chargehoraire.Models.AdminTask;
import com.fssm.chargehoraire.Models.AdminTaskKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminTaskRepository extends JpaRepository<AdminTask, AdminTaskKey> {
}

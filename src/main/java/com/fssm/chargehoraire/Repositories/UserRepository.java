package com.fssm.chargehoraire.Repositories;

import com.fssm.chargehoraire.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

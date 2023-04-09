package com.fssm.chargehoraire.Repositories;

import com.fssm.chargehoraire.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    @Query("SELECT CASE TYPE(u) WHEN Admin THEN 'admin' WHEN Teacher THEN 'teacher' END FROM User u WHERE  u.email = :EMAIL")
    Object[] getTypeByEmail(@Param("EMAIL") String email);
}

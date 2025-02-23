package com.alextechsolutions.serviceaheadversion5_0.Repository;

import com.alextechsolutions.serviceaheadversion5_0.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
    public Users findByUsername(String username);
}

package edu.miu.cse.finalproject.repository;

import edu.miu.cse.finalproject.model.User;
import edu.miu.cse.finalproject.util.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    List<User> findAllByRoleAndAvailability(Role role, boolean availability);
}

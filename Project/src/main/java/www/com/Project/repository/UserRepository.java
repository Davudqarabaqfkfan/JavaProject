package www.com.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import www.com.Project.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
boolean existsByUsername(String username);
}

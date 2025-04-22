package www.com.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import www.com.Project.entity.AuthorityE;

public interface AuthorityRepository extends JpaRepository<AuthorityE, Long> {

}

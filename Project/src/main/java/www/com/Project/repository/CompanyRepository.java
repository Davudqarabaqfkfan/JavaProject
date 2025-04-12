package www.com.Project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import www.com.Project.entity.CompanyEntity;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
	List<CompanyEntity> findByNameContainingIgnoreCase(String name);
	Page<CompanyEntity> findAll(Pageable pageable);
}

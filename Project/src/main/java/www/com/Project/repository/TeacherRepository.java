package www.com.Project.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import www.com.Project.entity.TeacherEntity;


@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
List<TeacherEntity> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name,String surname);

}
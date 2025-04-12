package www.com.Project.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import www.com.Project.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
FileEntity findByFileName(String fileName);
}

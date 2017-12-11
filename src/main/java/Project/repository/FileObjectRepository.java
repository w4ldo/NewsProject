package Project.repository;

import Project.domain.FileObject;
import Project.domain.NewsItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileObjectRepository extends JpaRepository<FileObject, Long> {

}

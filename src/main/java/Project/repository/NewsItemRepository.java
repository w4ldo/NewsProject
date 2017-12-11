package Project.repository;

import Project.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import Project.domain.NewsItem;
import java.util.Collection;

public interface NewsItemRepository extends JpaRepository<NewsItem, Long> {

    Collection<NewsItem> findByCategoriesContaining(Category category);
}

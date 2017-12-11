package Project.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Category extends AbstractPersistable<Long> {

    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<NewsItem> newsItems;

    public void addNewsToCategory(NewsItem news) {
        if (this.newsItems == null) {
            this.newsItems = new ArrayList<>();
        } else if (this.newsItems.contains(news)) {
            return;
        }
        this.newsItems.add(news);
    }

}

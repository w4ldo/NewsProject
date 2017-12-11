package Project.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class NewsItem extends AbstractPersistable<Long> {

    private String headline;
    private String lead;
    private String text;
    private LocalDate posted;
    @ManyToMany(mappedBy = "newsItems", fetch = FetchType.EAGER)
    private List<Category> categories;
    @OneToOne
    private FileObject fileObject;
    
    public void addCategoryToNews (Category category) {
        if (this.categories == null) {
            this.categories = new ArrayList<>();
        } else if (this.categories.contains(category)) {
            return;
        }
        this.categories.add(category);
    }
    
}

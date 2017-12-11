package Project.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class FileObject extends AbstractPersistable<Long> {

    private String name;
    private String mediaType;
    private Long size;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
    @OneToOne
    private NewsItem newsItem;
}

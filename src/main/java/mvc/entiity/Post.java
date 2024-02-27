package mvc.entiity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mvc.BaseEntity.BaseEntity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "base_id",sequenceName = "post_seq",allocationSize = 1)
public class Post extends BaseEntity {
    private String title;
    private String description;
    private Date created_at = Date.from(Instant.now());

@ManyToOne
    private User user;
@OneToMany(mappedBy = "post")
private List<Comment> comments;

@OneToMany(mappedBy = "post")
private List<Like>likes;

@OneToMany(mappedBy = "post",cascade = {CascadeType.REMOVE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    private List<Image> images;


    public void addUser(User user) {
    }
    public void addImage(Image image){
        if (images == null) images = new ArrayList<>();
        images.add(image);
    }
}

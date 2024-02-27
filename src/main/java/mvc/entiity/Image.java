package mvc.entiity;

import jakarta.persistence.*;
import lombok.*;
import mvc.BaseEntity.BaseEntity;

import java.util.List;

@Entity
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SequenceGenerator(name = "base_id",sequenceName = "image_seq",allocationSize = 1)
public class Image extends BaseEntity {

    private  String image_url;

@ManyToOne(cascade = CascadeType.PERSIST)
    private Post post;
@OneToMany
    private List<User> users;

}

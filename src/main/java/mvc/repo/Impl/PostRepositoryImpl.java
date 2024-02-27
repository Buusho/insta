package mvc.repo.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mvc.entiity.Image;
import mvc.entiity.Post;
import mvc.entiity.User;
import mvc.entiity.UserInfo;
import mvc.repo.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
@Transactional
@RequiredArgsConstructor
public class PostRepositoryImpl  implements PostRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Post> getAll() {
        return entityManager.createQuery("select s from Post  s",Post.class).getResultList();
    }

    @Override
    public void createPostWithImageAndLikes(Long userId, Post post) {
        User user = entityManager.find(User.class,userId);
        post.setUser(user);
        entityManager.persist(post);
    }

    @Override
    public Post findPostById(Long id) {

            return entityManager.find(Post.class, id);

    }
    @Override
    public String updatePostById(Long id, Post newPost) {
        entityManager.getTransaction().begin();
        Post post = entityManager.find(Post.class,id);
        post.setTitle(newPost.getTitle());
        post.setDescription(newPost.getDescription());
        entityManager.getTransaction().commit();

            return "updated successfully";
    }

    @Override
    public void addUserToPost(Long postId, Long userId) {
        Post post = entityManager.find(Post.class, postId);
        if (post == null) {
            throw new EntityNotFoundException("Post with id " + postId + " not found");
        }
        User user = entityManager.find(User.class, userId);
        if (user == null) {
            throw new EntityNotFoundException("User with id " + userId + " not found");
        }
        post.addUser(user);
        entityManager.merge(post);
    }

    @Override
    public void deletePostById(Long id) {
entityManager.remove(entityManager.find(Post.class,id));
    }
}

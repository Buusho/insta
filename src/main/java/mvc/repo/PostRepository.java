package mvc.repo;

import mvc.entiity.Post;

import java.util.List;

public interface PostRepository {
    List<Post> getAll ();
void createPostWithImageAndLikes(Long userId ,Post post);


Post findPostById(Long id);

String updatePostById(Long id, Post newPost);

void addUserToPost(Long postId, Long userId);

void  deletePostById (Long id);

}

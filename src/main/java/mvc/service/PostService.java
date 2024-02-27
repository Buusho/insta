package mvc.service;

import mvc.entiity.Post;

import java.util.List;

public interface PostService {
    List<Post> getAll ();
    void createPostWithImageAndLikes(Long userID ,Post post);
    Post findPostById(Long id);

    String updatePostById(Long id, Post newPost);

    void addUserToPost(Long postId, Long userId);
    void  deletePostById (Long id);
}

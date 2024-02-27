package mvc.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mvc.entiity.Image;
import mvc.entiity.Like;
import mvc.entiity.Post;
import mvc.repo.PostRepository;
import mvc.service.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public List<Post> getAll() {
        return postRepository.getAll();
    }

    @Override
    @Transactional
    public void createPostWithImageAndLikes(Long userId ,Post post) {
String imageURL = post.getImages().toString();
Image image = new Image();
image.setImage_url(imageURL);
        image.setPost(post);
       post.addImage(image);
postRepository.createPostWithImageAndLikes(userId,post);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findPostById(id);
    }

    @Override
    public String updatePostById(Long id, Post newPost) {
        return postRepository.updatePostById(id,newPost);
    }

    @Override
    public void addUserToPost(Long postId, Long userId) {
postRepository.addUserToPost(postId,userId);
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deletePostById(id);
    }
}

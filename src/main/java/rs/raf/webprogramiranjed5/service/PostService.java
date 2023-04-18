package rs.raf.webprogramiranjed5.service;

import rs.raf.webprogramiranjed5.model.Post;
import rs.raf.webprogramiranjed5.repository.post.IPostRepository;

import javax.inject.Inject;
import java.util.List;

public class PostService {

    @Inject
    private IPostRepository postRepository;

    public List<Post> getAll() {
        return postRepository.getAll();
    }

    public Post getByID(int id) {
        return postRepository.getByID(id);
    }

    public Post create(Post post) {
        return postRepository.create(post);
    }
}

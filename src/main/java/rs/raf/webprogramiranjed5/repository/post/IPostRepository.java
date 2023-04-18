package rs.raf.webprogramiranjed5.repository.post;

import rs.raf.webprogramiranjed5.model.Post;

import java.util.List;

public interface IPostRepository {
    List<Post> getAll();
    Post getByID(int id);
    Post create(Post post);
}

package rs.raf.webprogramiranjed5.repository.post;
import rs.raf.webprogramiranjed5.model.Post;
import rs.raf.webprogramiranjed5.util.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilePostRepository implements IPostRepository {

    private int idCounter = 0;

    public FilePostRepository() {

    }

    @Override
    public List<Post> getAll() {
        return FileManager.toList(Post[].class, "files/posts.json");
    }

    @Override
    public Post getByID(int id) {
        List<Post> posts = FileManager.toList(Post[].class, "files/posts.json");
        if (posts == null) return null;

        for (Post post : new ArrayList<>(posts)) {
            if (post.getId() == id) return post;
        }
        return null;
    }

    @Override
    public Post create(Post post) {
        // Get posts
        List<Post> posts = FileManager.toList(Post[].class, "files/posts.json");
        if (posts == null) return null;

        if (!posts.isEmpty()) idCounter = posts.get(posts.size() - 1).getId() + 1;

        // Set new post id
        post.setId(idCounter);

        // Add new post to list
        posts.add(post);

        // Save to file
        File file = FileManager.toFile(posts, "files/posts.json");
        if (file == null) return null;

        return post;
    }
}

package rs.raf.webprogramiranjed5.repository.comment;

import rs.raf.webprogramiranjed5.model.Comment;
import rs.raf.webprogramiranjed5.model.Post;
import rs.raf.webprogramiranjed5.util.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileCommentRepository implements ICommentRepository {

    private int idCounter = 0;

    @Override
    public List<Comment> getAll() {
        return FileManager.toList(Comment[].class, "files/comments.json");
    }

    @Override
    public Comment getByID(int id) {
        List<Comment> comments = FileManager.toList(Comment[].class, "files/comments.json");
        if (comments == null) return null;

        for (Comment comment : comments) {
            if (comment.getId() == id) return comment;
        }
        return null;
    }

    @Override
    public List<Comment> getByPostID(int postID) {
        List<Comment> comments = FileManager.toList(Comment[].class, "files/comments.json");
        List<Comment> filtered = new ArrayList<>();
        if (comments == null) return null;

        for (Comment comment : comments) {
            if (comment.getPostID() == postID) filtered.add(comment);
        }
        return filtered;
    }

    @Override
    public Comment create(Comment comment) {
        // Get posts
        List<Comment> comments = FileManager.toList(Comment[].class, "files/comments.json");
        if (comments == null) return null;

        if (!comments.isEmpty()) idCounter = comments.get(comments.size() - 1).getId() + 1;

        // Set new post id
        comment.setId(idCounter);

        // Add new post to list
        comments.add(comment);

        // Save to file
        File file = FileManager.toFile(comments, "files/comments.json");
        if (file == null) return null;

        return comment;
    }
}

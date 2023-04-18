package rs.raf.webprogramiranjed5.repository.comment;

import rs.raf.webprogramiranjed5.model.Comment;
import rs.raf.webprogramiranjed5.model.Post;

import java.util.List;

public interface ICommentRepository {
    List<Comment> getAll();
    Comment getByID(int id);

    List<Comment> getByPostID(int postID);
    Comment create(Comment post);
}

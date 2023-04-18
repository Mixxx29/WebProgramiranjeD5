package rs.raf.webprogramiranjed5.service;

import rs.raf.webprogramiranjed5.model.Comment;
import rs.raf.webprogramiranjed5.model.Post;
import rs.raf.webprogramiranjed5.repository.comment.ICommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {
    @Inject
    private ICommentRepository commentRepository;

    public List<Comment> getAll() {
        return commentRepository.getAll();
    }

    public Comment getByID(int id) {
        return commentRepository.getByID(id);
    }

    public List<Comment> getByPostID(int postID) {
        return commentRepository.getByPostID(postID);
    }

    public Comment create(Comment comment) {
        return commentRepository.create(comment);
    }
}

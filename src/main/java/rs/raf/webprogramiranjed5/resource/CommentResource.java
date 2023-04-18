package rs.raf.webprogramiranjed5.resource;

import rs.raf.webprogramiranjed5.model.Comment;
import rs.raf.webprogramiranjed5.model.Post;
import rs.raf.webprogramiranjed5.service.CommentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/comments")
public class CommentResource {

    @Inject
    private CommentService commentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(commentService.getAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByID(@PathParam("id") Integer id) {
        return Response.ok(commentService.getByID(id)).build();
    }

    @GET
    @Path("/postID/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByPostID(@PathParam("id") Integer postID) {
        return Response.ok(commentService.getByPostID(postID)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid Comment comment) {
        comment = commentService.create(comment);
        if (comment == null) return Response.status(404).build();
        return Response.ok(comment).build();
    }
}

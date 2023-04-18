package rs.raf.webprogramiranjed5.resource;

import rs.raf.webprogramiranjed5.model.Post;
import rs.raf.webprogramiranjed5.service.PostService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/posts")
public class PostResource {

    @Inject
    private PostService postService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(postService.getAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByID(@PathParam("id") Integer id) {
        return Response.ok(postService.getByID(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid Post post) {
        post = postService.create(post);
        if (post == null) return Response.status(404).build();
        return Response.ok(post).build();
    }
}
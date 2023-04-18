package rs.raf.webprogramiranjed5.app;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import rs.raf.webprogramiranjed5.repository.comment.FileCommentRepository;
import rs.raf.webprogramiranjed5.repository.comment.ICommentRepository;
import rs.raf.webprogramiranjed5.repository.post.FilePostRepository;
import rs.raf.webprogramiranjed5.repository.post.IPostRepository;
import rs.raf.webprogramiranjed5.service.CommentService;
import rs.raf.webprogramiranjed5.service.PostService;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class Application extends ResourceConfig {

    public Application() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Inject dependencies
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(FilePostRepository.class).to(IPostRepository.class).in(Singleton.class);
                this.bindAsContract(PostService.class).in(Singleton.class);

                this.bind(FileCommentRepository.class).to(ICommentRepository.class).in(Singleton.class);
                this.bindAsContract(CommentService.class).in(Singleton.class);
            }
        };
        register(binder);

        // Load resources
        packages("rs.raf.webprogramiranjed5.resource");
    }
}
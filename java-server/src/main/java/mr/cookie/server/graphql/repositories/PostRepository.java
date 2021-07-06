package mr.cookie.server.graphql.repositories;

import mr.cookie.server.graphql.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
    
}

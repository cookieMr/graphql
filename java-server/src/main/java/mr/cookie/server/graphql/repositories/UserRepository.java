package mr.cookie.server.graphql.repositories;

import mr.cookie.server.graphql.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    
}

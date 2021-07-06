package mr.cookie.server.graphql.repositories;

import mr.cookie.server.graphql.models.Hobby;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HobbyRepository extends MongoRepository<Hobby, String> {
    
}

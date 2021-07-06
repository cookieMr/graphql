package mr.cookie.server.graphql.models;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Builder
@Document(collection = "hobbies")
public class Hobby {
    
    @Id
    private String id;
    private String title;
    private String description;
    private String userId;

}

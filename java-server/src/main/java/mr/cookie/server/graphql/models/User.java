package mr.cookie.server.graphql.models;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Builder
@Document(collection = "users")
public class User {
    
    @Id
    private String id;
    private String name;
    private Integer age;
    private String profession;

}

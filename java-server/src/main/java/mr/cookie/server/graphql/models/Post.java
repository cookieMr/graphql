package mr.cookie.server.graphql.models;

import java.time.LocalDate;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Builder
@Document(collection = "posts")
public class Post {

    @Id
    private String id;
    private String comment;
    private String userId;
    private LocalDate createdAt;

}

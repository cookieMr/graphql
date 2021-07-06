package mr.cookie.server.graphql.resolvers;

import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mr.cookie.server.graphql.models.Hobby;
import mr.cookie.server.graphql.models.Post;
import mr.cookie.server.graphql.models.User;
import mr.cookie.server.graphql.repositories.HobbyRepository;
import mr.cookie.server.graphql.repositories.PostRepository;
import mr.cookie.server.graphql.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {

    private final @NotNull HobbyRepository hobbyRepository;
    private final @NotNull PostRepository postRepository;
    private final @NotNull UserRepository userRepository;

    public @Nullable User user(@Nullable String id) {
        if (id == null) {
            return null;
        }
        return userRepository.findById(id)
            .orElse(null);
    }

    public @NotNull List<User> users() {
        return userRepository.findAll();
    }

    public @Nullable Post post(@Nullable String id) {
        if (id == null) {
            return null;
        }
        return postRepository.findById(id)
            .orElse(null);
    }

    public @NotNull List<Post> posts() {
        return postRepository.findAll();
    }

    public @Nullable Hobby hobby(@Nullable String id) {
        if (id == null) {
            return null;
        }
        return hobbyRepository.findById(id)
            .orElse(null);
    }

    public @NotNull List<Hobby> hobbies() {
        return hobbyRepository.findAll();
    }

}

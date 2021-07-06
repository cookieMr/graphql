package mr.cookie.server.graphql.resolvers;

import graphql.kickstart.tools.GraphQLResolver;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mr.cookie.server.graphql.models.Post;
import mr.cookie.server.graphql.models.User;
import mr.cookie.server.graphql.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostUserResolver implements GraphQLResolver<Post> {

    private final @NotNull UserRepository userRepository;

    public @Nullable CompletableFuture<User> user(@NotNull Post post) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Fetching user inside an async supplier.");
            return Optional.of(post)
                .map(Post::getUserId)
                .map(userRepository::findById)
                .flatMap(Function.identity())
                .orElse(null);
        });
    }

}

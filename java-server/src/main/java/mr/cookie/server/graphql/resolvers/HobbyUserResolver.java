package mr.cookie.server.graphql.resolvers;

import graphql.kickstart.tools.GraphQLResolver;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import mr.cookie.server.graphql.models.Hobby;
import mr.cookie.server.graphql.models.User;
import mr.cookie.server.graphql.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HobbyUserResolver implements GraphQLResolver<Hobby> {

    private final @NotNull UserRepository userRepository;

    public @Nullable CompletableFuture<User> user(@NotNull Hobby hobby) {
        return CompletableFuture.supplyAsync(() -> Optional.of(hobby)
            .map(Hobby::getUserId)
            .map(userRepository::findById)
            .flatMap(Function.identity())
            .orElse(null));
    }

}

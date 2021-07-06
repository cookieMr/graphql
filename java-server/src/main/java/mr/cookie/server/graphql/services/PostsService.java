package mr.cookie.server.graphql.services;

import mr.cookie.server.graphql.models.Post;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public interface PostsService {
    
    Optional<Post> getById(@Nullable String id);

    @NotNull List<Post> getAll();

    @NotNull Post create(@Nullable Post post);

    void deleteById(@Nullable String id);

}

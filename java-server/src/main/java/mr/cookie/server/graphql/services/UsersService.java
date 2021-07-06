package mr.cookie.server.graphql.services;

import mr.cookie.server.graphql.models.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    
    Optional<User> getById(@Nullable String id);

    @NotNull List<User> getAll();

    @NotNull User create(@Nullable User user);

    void deleteById(@Nullable String id);

}

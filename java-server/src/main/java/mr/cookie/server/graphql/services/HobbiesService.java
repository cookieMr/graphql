package mr.cookie.server.graphql.services;

import mr.cookie.server.graphql.models.Hobby;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public interface HobbiesService {
    
    Optional<Hobby> getById(@Nullable String id);

    @NotNull List<Hobby> getAll();

    @NotNull Hobby create(@Nullable Hobby hobby);

    void deleteById(@Nullable String id);

}

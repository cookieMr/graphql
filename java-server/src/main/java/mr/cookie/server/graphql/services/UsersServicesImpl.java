package mr.cookie.server.graphql.services;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mr.cookie.server.graphql.models.User;
import mr.cookie.server.graphql.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServicesImpl implements UsersService {

    private final @NotNull UserRepository userRepository;

    @Override
    public Optional<User> getById(@Nullable String id) {
        if (id == null) {
            return Optional.empty();
        }
        return userRepository.findById(id);
    }

    @Override
    public @NotNull List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public @NotNull User create(@Nullable User user) {
        if (user == null) {
            throw new IllegalArgumentException("Input user for creation cannot be null!");
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteById(@Nullable String id) {
        Optional.ofNullable(id)
            .ifPresent(userRepository::deleteById);
    }

}

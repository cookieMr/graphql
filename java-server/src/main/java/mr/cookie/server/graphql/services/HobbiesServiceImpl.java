package mr.cookie.server.graphql.services;

import mr.cookie.server.graphql.models.Hobby;
import mr.cookie.server.graphql.repositories.HobbyRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HobbiesServiceImpl implements HobbiesService {
    
    private final @NotNull HobbyRepository hobbyRepository;

    @Override
    public Optional<Hobby> getById(@Nullable String id) {
        if (id == null) {
            return Optional.empty();
        }
        return hobbyRepository.findById(id);
    }

    @Override
    public @NotNull List<Hobby> getAll() {
        return hobbyRepository.findAll();
    }

    @Override
    public @NotNull Hobby create(@Nullable Hobby hobby) {
        if (hobby == null) {
            throw new IllegalArgumentException("Input hobby for creation cannot be null!");
        }

        return hobbyRepository.save(hobby);
     }

    @Override
    public void deleteById(@Nullable String id) {
        Optional.ofNullable(id)
            .ifPresent(hobbyRepository::deleteById);
    }

}

package mr.cookie.server.graphql.services;

import mr.cookie.server.graphql.models.Post;
import mr.cookie.server.graphql.repositories.PostRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {
    
    private final @NotNull PostRepository postRepository;

    @Override
    public Optional<Post> getById(@Nullable String id) {
        if (id == null) {
            return Optional.empty();
        }
        return postRepository.findById(id);
    }

    @Override
    public @NotNull List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public @NotNull Post create(@Nullable Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Input post for creation cannot be null!");
        }

        return postRepository.save(post);
    }

    @Override
    public void deleteById(@Nullable String id) {
        Optional.ofNullable(id)
            .ifPresent(postRepository::deleteById);
    }

}

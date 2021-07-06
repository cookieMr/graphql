package mr.cookie.server.graphql.resolvers;

import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Optional;
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
public class MutationResolver implements GraphQLMutationResolver {

    private final @NotNull Clock clock;
    private final @NotNull HobbyRepository hobbyRepository;
    private final @NotNull PostRepository postRepository;
    private final @NotNull UserRepository userRepository;

    public @NotNull User createUser(
        @NotNull String name,
        @Nullable Integer age,
        @Nullable String profession) {
        return userRepository.save(User.builder()
            .name(name)
            .age(age)
            .profession(profession)
            .build());
    }

    public @NotNull User updateUser(
        @NotNull String id,
        @Nullable String name,
        @Nullable Integer age,
        @Nullable String profession) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new GraphQLException(
                String.format("Cannot find a user with the provided id: %s", id)));

        Optional.ofNullable(name).ifPresent(user::setName);
        Optional.ofNullable(age).ifPresent(user::setAge);
        Optional.ofNullable(profession).ifPresent(user::setProfession);

        return userRepository.save(user);
    }

    public @Nullable User removeUser(@NotNull String id) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(user -> userRepository.deleteById(id));
        return userOptional.orElse(null);
    }

    public @NotNull Post createPost(@NotNull String comment, @NotNull String userId) {
        return postRepository.save(Post.builder()
            .comment(comment)
            .userId(userId)
            .createdAt(LocalDate.now(clock))
            .build());
    }

    public @NotNull Post updatePost(
        @NotNull String id,
        @Nullable String comment,
        @Nullable String userId) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new GraphQLException(
                String.format("Cannot find a post with the provided id: %s", id)));

        Optional.ofNullable(comment).ifPresent(post::setComment);
        Optional.ofNullable(userId).ifPresent(post::setUserId);

        return postRepository.save(post);
    }

    public @Nullable Post removePost(@NotNull String id) {
        Optional<Post> userOptional = postRepository.findById(id);
        userOptional.ifPresent(user -> postRepository.deleteById(id));
        return userOptional.orElse(null);
    }

    public @NotNull Hobby createHobby(
        @NotNull String title,
        @Nullable String description,
        @NotNull String userId) {
        return hobbyRepository.save(Hobby.builder()
            .title(title)
            .description(description)
            .userId(userId)
            .build());
    }

    public @NotNull Hobby updateHobby(
        @NotNull String id,
        @Nullable String title,
        @Nullable String description,
        @Nullable String userId) {
        Hobby hobby = hobbyRepository.findById(id)
            .orElseThrow(() -> new GraphQLException(
                String.format("Cannot find a hobby with the provided id: %s", id)));

        Optional.ofNullable(title).ifPresent(hobby::setTitle);
        Optional.ofNullable(description).ifPresent(hobby::setDescription);
        Optional.ofNullable(userId).ifPresent(hobby::setUserId);

        return hobbyRepository.save(hobby);
    }

    public @Nullable Hobby removeHobby(@NotNull String id) {
        Optional<Hobby> userOptional = hobbyRepository.findById(id);
        userOptional.ifPresent(user -> hobbyRepository.deleteById(id));
        return userOptional.orElse(null);
    }

}

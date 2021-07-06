package mr.cookie.server.graphql.configs;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mr.cookie.server.graphql.models.Hobby;
import mr.cookie.server.graphql.models.Post;
import mr.cookie.server.graphql.models.User;
import mr.cookie.server.graphql.repositories.HobbyRepository;
import mr.cookie.server.graphql.repositories.PostRepository;
import mr.cookie.server.graphql.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitDataCommandLineRunner implements CommandLineRunner {

    private final @NotNull Clock clock;
    private final @NotNull HobbyRepository hobbyRepository;
    private final @NotNull PostRepository postRepository;
    private final @NotNull UserRepository userRepository;

    @Override
    public void run(@Nullable String... args) throws ExecutionException, InterruptedException {
        final String userId = createUserIfTableIsEmpty();

        CompletableFuture<Void> postFuture = CompletableFuture.runAsync(() -> createPostIfTableIsEmpty(userId));
        CompletableFuture<Void> hobbyFuture = CompletableFuture.runAsync(() -> createHobbyIfTableIsEmpty(userId));

        CompletableFuture.allOf(postFuture, hobbyFuture).get();
    }

    private @NotNull String createUserIfTableIsEmpty() {
        List<User> users = userRepository.findAll();
        log.info("Mongo had {} users.", users.size());

        if (users.isEmpty()) {
            log.info("Inserting 1 user into Mongo DB.");
            return userRepository.save(User.builder()
                .name("Dave")
                .age(29)
                .profession("The IT Guy")
                .build())
                .getId();
        }

        log.info("Mongo has {} users.", userRepository.findAll().size());
        return users.iterator().next().getId();
    }

    private void createPostIfTableIsEmpty(@NotNull String userId) {
        List<Post> posts = postRepository.findAll();
        log.info("Mongo had {} posts.", posts.size());

        if (posts.isEmpty()) {
            postRepository.save(Post.builder()
                .comment("I love my job!")
                .userId(userId)
                .createdAt(LocalDate.now(clock))
                .build());
        }

        log.info("Mongo has {} posts.", postRepository.findAll().size());
    }

    private void createHobbyIfTableIsEmpty(@NotNull String userId) {
        List<Hobby> hobbies = hobbyRepository.findAll();
        log.info("Mongo had {} hobbies.", hobbies.size());

        if (hobbies.isEmpty()) {
            hobbyRepository.save(Hobby.builder()
                .title("Writing apps!")
                .description("Making the world a faster place!")
                .userId(userId)
                .build());
        }

        log.info("Mongo has {} hobbies.", hobbyRepository.findAll().size());
    }

}

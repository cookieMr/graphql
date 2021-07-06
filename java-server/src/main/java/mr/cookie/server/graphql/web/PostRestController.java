package mr.cookie.server.graphql.web;

import java.util.List;

import mr.cookie.server.graphql.models.Post;
import mr.cookie.server.graphql.services.PostsService;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PostRestController {
    
    private final @NotNull PostsService postsService;

    @GetMapping("/{id}")
    public @Nullable Post getPost(@Nullable @PathVariable("id") String id) {
        return postsService.getById(id)
            .orElse(null);
    }

    @GetMapping("/all")
    public @NotNull List<Post> getAllPosts() {
        return postsService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @Nullable Post createPost(@Nullable @RequestBody Post post) {
        return postsService.create(post);
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@Nullable @PathVariable("id") String id) {
        postsService.deleteById(id);
    }

}

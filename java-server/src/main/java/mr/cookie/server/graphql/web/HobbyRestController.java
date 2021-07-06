package mr.cookie.server.graphql.web;

import java.util.List;

import mr.cookie.server.graphql.models.Hobby;
import mr.cookie.server.graphql.services.HobbiesService;

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
@RequestMapping(path = "/hobbies", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class HobbyRestController {
    
    private final @NotNull HobbiesService hobbiesService;

    @GetMapping("/{id}")
    public @Nullable Hobby getHobby(@Nullable @PathVariable("id") String id) {
        return hobbiesService.getById(id)
            .orElse(null);
    }

    @GetMapping("/all")
    public @NotNull List<Hobby> getAllHobbies() {
        return hobbiesService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @Nullable Hobby createHobby(@Nullable @RequestBody Hobby post) {
        return hobbiesService.create(post);
    }

    @DeleteMapping("/{id}")
    public void deleteHobbyById(@Nullable @PathVariable("id") String id) {
        hobbiesService.deleteById(id);
    }

}

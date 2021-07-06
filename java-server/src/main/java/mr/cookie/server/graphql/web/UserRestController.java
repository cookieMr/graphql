package mr.cookie.server.graphql.web;

import java.util.List;

import mr.cookie.server.graphql.models.User;
import mr.cookie.server.graphql.services.UsersService;

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
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserRestController {
    
    private final @NotNull UsersService usersService;

    @GetMapping("/{id}")
    public @Nullable User getUser(@Nullable @PathVariable("id") String id) {
        return usersService.getById(id)
            .orElse(null);
    }

    @GetMapping("/all")
    public @NotNull List<User> getAllUsers() {
        return usersService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @Nullable User createUser(@Nullable @RequestBody User user) {
        return usersService.create(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@Nullable @PathVariable("id") String id) {
        usersService.deleteById(id);
    }

}

package mr.cookie.server.graphql.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class SwaggerConfiguration {
    
    @Value("${spring.application.name:#{null}}")
    private @Nullable String appName;

    @Value("${spring.application.version:#{null}}")
    private @Nullable String version;

    @Value("${spring.application.description:#{null}}")
    private @Nullable String description;

    @Value("${developer.name:#{null}}")
    private @Nullable String developerName;

    @Value("${developer.email:#{null}}")
    private @Nullable String developerEmail;

    @Value("${developer.url:#{null}}")
    private @Nullable String developerUrl;

    @Bean
    public @NotNull OpenAPI openApi(@NotNull Info info) {
        return new OpenAPI()
            .info(info);
    }

    @Bean
    public @NotNull Info info(
        @NotNull Contact contact,
        @NotNull License license) {
        return new Info()
            .title(appName)
            .description(description)
            .version(version)
            .contact(contact)
            .license(license);
    }

    @Bean
    public @NotNull Contact contact() {
        return new Contact()
            .email(developerEmail)
            .name(developerName)
            .url(developerUrl);
    }

    @Bean
    public @NotNull License license() {
        return new License()
            .name("GPL v3")
            .url("https://www.gnu.org/licenses/gpl-3.0.en.html");
    }

}

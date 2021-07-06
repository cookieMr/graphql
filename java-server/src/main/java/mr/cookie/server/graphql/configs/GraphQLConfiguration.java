package mr.cookie.server.graphql.configs;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import java.time.Clock;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GraphQLConfiguration {

    @Bean
    public @NotNull Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public @NotNull GraphQLScalarType positiveInt() {
        return ExtendedScalars.PositiveInt;
    }

    @Bean
    public @NotNull GraphQLScalarType date() {
        return ExtendedScalars.Date;
    }

}

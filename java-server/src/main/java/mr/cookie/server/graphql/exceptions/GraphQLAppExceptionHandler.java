package mr.cookie.server.graphql.exceptions;

import graphql.GraphQLException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GraphQL will use these handles only if the exception handling is turned on in
 * app properties. To do so set @{code graphql.servlet.exception-handlers-enabled: true}.
 */
@Slf4j
@Component
public class GraphQLAppExceptionHandler {

    private static final AtomicInteger counter = new AtomicInteger(0);

    @ExceptionHandler(GraphQLException.class)
    public ThrowableGraphQLError handle(GraphQLException exception) {
        log.info("Handling an exception {}.", counter.incrementAndGet());
        return new ThrowableGraphQLError(exception);
    }

}

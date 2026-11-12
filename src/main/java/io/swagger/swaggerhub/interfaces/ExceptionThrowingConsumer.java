package io.swagger.swaggerhub.interfaces;

import java.util.function.Consumer;

/**
 * Functional interface similar to {@link Consumer} whose {@link #accept(Object)} may throw a checked exception.
 * Use {@link #RuntimeThrowingConsumerWrapper(ExceptionThrowingConsumer)} to use it where a plain {@link Consumer} is required, e.g. in a stream.
 *
 * @see <a href="https://www.baeldung.com/java-lambda-exceptions">https://www.baeldung.com/java-lambda-exceptions</a>
 * @param <T> type of the consumed value
 * @param <E> type of the exception that may be thrown
 */
@FunctionalInterface
public interface ExceptionThrowingConsumer<T, E extends Exception> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     * @throws E if the operation fails
     */
    void accept(T t) throws E;

    /**
     * Wraps the given consumer in a plain {@link Consumer}. Any exception thrown by it is rethrown as a {@link RuntimeException} with the original exception as its cause.
     *
     * @param exceptionThrowingConsumer the consumer to wrap
     * @param <T> type of the consumed value
     * @return a consumer which does not declare checked exceptions
     */
    static <T> Consumer<T> RuntimeThrowingConsumerWrapper(
            ExceptionThrowingConsumer<T, Exception> exceptionThrowingConsumer) {

        return i -> {
            try {
                exceptionThrowingConsumer.accept(i);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }
}
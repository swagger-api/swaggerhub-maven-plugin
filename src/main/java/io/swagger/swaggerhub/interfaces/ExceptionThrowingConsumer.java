package io.swagger.swaggerhub.interfaces;

import java.util.function.Consumer;

/**
 * Consumer used throw an exception from the given funtion
 * @param <T>
 * @param <E>
 */
@FunctionalInterface
public interface ExceptionThrowingConsumer<T, E extends Exception> {

    void accept(T t) throws E;

    /**
     * Consumer wrapper that catches a thrown exception and wraps that in a Runtime Exception.
     * @param exceptionThrowingConsumer
     * @param <T>
     * @return
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
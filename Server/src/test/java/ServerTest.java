import itmo.p3108.chain.AuthorizeHandler;
import itmo.p3108.chain.Handler;
import itmo.p3108.command.Info;
import itmo.p3108.exception.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTest {
    @Test
    public void check_ormAnalyzer() {

        ExecutorService executor = Executors.newFixedThreadPool(10);
        Handler HANDLER = new AuthorizeHandler();
        Runnable serverReply = () -> {
            String result;
            try {
                result = HANDLER.processRequest(new Info(), "ada");
            } catch (ValidationException validationException) {
                result = validationException.toString();
            }
        };
        CompletableFuture.runAsync(serverReply, executor);
    }
}

package no.nav.foreldrepenger.fpdokgen.server;

import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.server.ServerConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import io.javalin.Javalin;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import no.nav.foreldrepenger.fpdokgen.server.app.api.ApiConfig;
import no.nav.foreldrepenger.fpdokgen.server.app.internal.InternalApiConfig;
import no.nav.foreldrepenger.konfig.Environment;

public class JavalinServer {

    private static final Logger LOG = LoggerFactory.getLogger(JavalinServer.class);
    private static final Environment ENV = Environment.current();
    private static final String CONTEXT_PATH = ENV.getProperty("context.path", "/fpdokgen");

    private final int serverPort;

    protected JavalinServer(int serverPort) {
        this.serverPort = serverPort;
    }

    static void main() {
        new JavalinServer(ENV.getProperty("server.port", Integer.class, 8080)).bootStrap();
    }

    protected void bootStrap() {
        konfigurerLogging();
        start();
    }

    /**
     * Vi bruker SLF4J + logback. Bro fra JUL slik at evt biblioteker som logger
     * gjennom JUL ender opp i Logback.
     */
    private static void konfigurerLogging() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    private void start() {
        LOG.info("Starter server");

        var container = SeContainerInitializer.newInstance().initialize();

        var app = Javalin.create(cfg -> {
            cfg.startup.showJavalinBanner  = false;
            cfg.router.contextPath = CONTEXT_PATH;
            cfg.jsonMapper(JsonConfig.jsonMapper());
            cfg.jetty.modifyServer(server -> {
                for (var connector : server.getConnectors()) {
                    if (connector instanceof ServerConnector sc) {
                        sc.setIdleTimeout(TimeUnit.MINUTES.toMillis(2));
                    }
                }
                server.setStopAtShutdown(true);
                server.setStopTimeout(10_000);
            });

            PathAllowList.register(cfg.routes, CONTEXT_PATH + ApiConfig.API_URI, CONTEXT_PATH + InternalApiConfig.API_URI);
            CdiRequestScope.register(cfg.routes, container);
            ExceptionMappers.register(cfg.routes);

            InternalApiConfig.register(cfg.routes, container);
            ApiConfig.register(cfg.routes, container);
        });

        // Weld registrerer sin egen shutdown-hook for SE-kontaineren; vi trenger bare å stoppe Javalin.
        Runtime.getRuntime().addShutdownHook(new Thread(app::stop, "javalin-shutdown"));

        app.start(serverPort);
        LOG.info("Server startet på port: {}", serverPort);
    }
}

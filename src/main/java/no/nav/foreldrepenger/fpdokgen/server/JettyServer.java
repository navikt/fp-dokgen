package no.nav.foreldrepenger.fpdokgen.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import no.nav.foreldrepenger.fpdokgen.server.app.api.ApiConfig;
import no.nav.foreldrepenger.fpdokgen.server.app.internal.InternalApiConfig;
import no.nav.foreldrepenger.konfig.Environment;
import no.nav.vedtak.server.jetty.JettyServerBuilder;

public class JettyServer {
    private static final Logger LOG = LoggerFactory.getLogger(JettyServer.class);
    private static final Environment ENV = Environment.current();

    private static final String CONTEXT_PATH = ENV.getProperty("context.path", "/fpdokgen");

    private final Integer serverPort;

    JettyServer(int serverPort) {
        this.serverPort = serverPort;
    }

    static void main() throws Exception {
        jettyServer().bootStrap();
    }

    private static JettyServer jettyServer() {
        return new JettyServer(ENV.getProperty("server.port", Integer.class, 8080));
    }

    void bootStrap() throws Exception {
        konfigurerLogging();
        start();
    }

    /**
     * Vi bruker SLF4J + logback, Jersey brukes JUL for logging.
     * Setter opp en bridge til å få Jersey til å logge gjennom Logback også.
     */
    private static void konfigurerLogging() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    private void start() throws Exception {
        LOG.info("Starter server");
        var server = JettyServerBuilder.builder()
            .port(serverPort)
            .contextPath(CONTEXT_PATH)
            .registerRestApp(InternalApiConfig.API_URI, InternalApiConfig.class)
            .registerRestApp(ApiConfig.API_URI, ApiConfig.class)
            .build();
        server.start();
        LOG.info("Server startet på port: {}", serverPort);
        server.join();
    }
}

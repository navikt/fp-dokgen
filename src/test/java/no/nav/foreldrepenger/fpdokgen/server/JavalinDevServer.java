package no.nav.foreldrepenger.fpdokgen.server;

import no.nav.foreldrepenger.konfig.Environment;

public class JavalinDevServer extends JavalinServer {

    private static final Environment ENV = Environment.current();

    public static void main(String[] args) {
        devServer(args).bootStrap();
    }

    private static JavalinDevServer devServer(String[] args) {
        if (args.length > 0) {
            return new JavalinDevServer(Integer.parseUnsignedInt(args[0]));
        }
        return new JavalinDevServer(ENV.getProperty("server.port", Integer.class, 8291));
    }

    private JavalinDevServer(int serverPort) {
        super(serverPort);
    }
}

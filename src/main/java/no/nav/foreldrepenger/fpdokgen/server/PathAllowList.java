package no.nav.foreldrepenger.fpdokgen.server;

import io.javalin.config.RoutesConfig;
import io.javalin.http.HttpStatus;

final class PathAllowList {

    private PathAllowList() {
    }

    static void register(RoutesConfig routes, String... allowedPrefixes) {
        routes.before(ctx -> {
            var path = ctx.path();
            for (var prefix : allowedPrefixes) {
                if (path.startsWith(prefix)) {
                    return;
                }
            }
            ctx.status(HttpStatus.FORBIDDEN);
            ctx.skipRemainingHandlers();
        });
    }
}

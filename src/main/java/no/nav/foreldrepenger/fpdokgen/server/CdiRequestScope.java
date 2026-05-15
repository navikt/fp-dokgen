package no.nav.foreldrepenger.fpdokgen.server;

import io.javalin.config.RoutesConfig;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.inject.se.SeContainer;

final class CdiRequestScope {

    private static final String ATTR = "fpdokgen.cdi.requestScope";

    private CdiRequestScope() {
    }

    static void register(RoutesConfig routes, SeContainer container) {
        routes.before(ctx -> {
            var controller = container.select(RequestContextController.class).get();
            controller.activate();
            ctx.attribute(ATTR, controller);
        });
        routes.after(ctx -> {
            var controller = (RequestContextController) ctx.attribute(ATTR);
            if (controller != null) {
                controller.deactivate();
            }
        });
    }
}

package no.nav.foreldrepenger.fpdokgen.server.app.api;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import no.nav.vedtak.sikkerhet.kontekst.KontekstHolder;
import no.nav.vedtak.sikkerhet.kontekst.RequestKontekst;

@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext req) {
        if (!(KontekstHolder.getKontekst() instanceof RequestKontekst kontekst) || !kontekst.getIdentType().erSystem()) {
            KontekstHolder.fjernKontekst();
            throw new WebApplicationException("Kun systemkontekst er tillatt.", Response.Status.FORBIDDEN);
        }
    }

}

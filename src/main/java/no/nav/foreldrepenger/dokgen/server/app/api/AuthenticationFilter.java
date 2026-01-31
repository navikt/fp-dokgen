package no.nav.foreldrepenger.dokgen.server.app.api;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import no.nav.vedtak.sikkerhet.jaxrs.AuthenticationFilterDelegate;
import no.nav.vedtak.sikkerhet.kontekst.KontekstHolder;
import no.nav.vedtak.sikkerhet.kontekst.RequestKontekst;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Context
    private ResourceInfo resourceinfo;

    public AuthenticationFilter() {
        // Ingenting
    }

    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext res) {
        AuthenticationFilterDelegate.fjernKontekst();
    }

    @Override
    public void filter(ContainerRequestContext req) {
        AuthenticationFilterDelegate.validerSettKontekst(resourceinfo, req);

        if (!(KontekstHolder.getKontekst() instanceof RequestKontekst kontekst)) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
        if (!kontekst.getIdentType().erSystem()) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
    }

}

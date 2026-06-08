package no.nav.foreldrepenger.fpdokgen.server.app.internal.rest;

import static no.nav.vedtak.log.metrics.MetricsUtil.REGISTRY;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PrometheusRestService {

    public String prometheus() {
        return REGISTRY.scrape();
    }
}

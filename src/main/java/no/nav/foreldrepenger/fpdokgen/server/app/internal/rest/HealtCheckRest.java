package no.nav.foreldrepenger.fpdokgen.server.app.internal.rest;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import no.nav.vedtak.server.LivenessAware;
import no.nav.vedtak.server.ReadinessAware;

@ApplicationScoped
public class HealtCheckRest {

    private List<LivenessAware> live;
    private List<ReadinessAware> ready;

    @Inject
    public HealtCheckRest(@Any Instance<LivenessAware> live, @Any Instance<ReadinessAware> ready) {
        this.live = live.stream().toList();
        this.ready = ready.stream().toList();
    }

    HealtCheckRest() {
        //CDI
    }

    public boolean isAlive() {
        return live.stream().allMatch(LivenessAware::isAlive);
    }

    public boolean isReady() {
        return ready.stream().allMatch(ReadinessAware::isReady);
    }
}

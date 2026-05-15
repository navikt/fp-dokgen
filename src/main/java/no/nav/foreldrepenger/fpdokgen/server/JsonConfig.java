package no.nav.foreldrepenger.fpdokgen.server;

import io.javalin.json.JavalinJackson3;
import no.nav.vedtak.mapper.json.DefaultJson3Mapper;

final class JsonConfig {

    private JsonConfig() {
    }

    static JavalinJackson3 jsonMapper() {
        return new JavalinJackson3(DefaultJson3Mapper.getJsonMapper(), false);
    }
}

package no.nav.foreldrepenger.dokgen.server.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {

    private static final Logger LOG = LoggerFactory.getLogger(JsonParseExceptionMapper.class);

    @Override
    public Response toResponse(JsonParseException exception) {
        var feil = String.format("FPDOKGEN-299955: JSON-parsing feil: %s", exception.getMessage());
        LOG.warn(feil);
        return Response
            .status(Response.Status.BAD_REQUEST)
            .entity(new FeilDto(FeilDto.FeilType.JSON_PARSE_FEIL, feil))
            .type(MediaType.APPLICATION_JSON)
            .build();
    }
}

package no.nav.foreldrepenger.fpdokgen.server.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {

    private static final Logger LOG = LoggerFactory.getLogger(JsonMappingExceptionMapper.class);

    @Override
    public Response toResponse(JsonMappingException exception) {
        var feil = "FPDOKGEN-252294: JSON-mapping feil";
        LOG.warn(feil);
        return Response
            .status(Response.Status.BAD_REQUEST)
            .entity(new FeilDto(FeilDto.FeilType.JSON_PARSE_FEIL, feil))
            .type(MediaType.APPLICATION_JSON)
            .build();
    }
}

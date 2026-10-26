package art.biblio.common;

import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import jakarta.ws.rs.core.Response;

public class ExceptionMappers {

    @ServerExceptionMapper 
    public Response mapLivreIntrouvable(LivreIntrouvableException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErreurResponse(Response.Status.NOT_FOUND.getStatusCode(), exception.getMessage()))
                .build();
    }

    @ServerExceptionMapper
    public Response mapPlusDExemplaire(PlusDExemplaireException exception) {
        return Response.status(Response.Status.CONFLICT)
                .entity(new ErreurResponse(Response.Status.CONFLICT.getStatusCode(), exception.getMessage()))
                .build();
    }

}

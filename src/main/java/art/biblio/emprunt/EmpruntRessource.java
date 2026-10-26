package art.biblio.emprunt;

import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path ("/api/livres/{livreId}/emprunts")
public class EmpruntRessource {

    private final EmpruntService empruntService;

    public EmpruntRessource(EmpruntService empruntService) {
        this.empruntService = empruntService;
    }

    @POST
    public Response creerEmprunt(@PathParam("livreId") Long livreId, @Valid EmpruntRequest empruntRequest) {
        EmpruntResponse empruntResponse = empruntService.creerEmprunt(livreId, empruntRequest);
        return Response.status(Response.Status.CREATED).entity(empruntResponse).build();
    }



}

package art.biblio.livre;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/api/livres")
public class LivreRessource {

    private final LivreService livreService;

    public LivreRessource(LivreService livreService) {
        this.livreService = livreService;
    }

    @GET 
    public List<LivreResponse> rechercherLivres(@QueryParam("auteur") String auteur) {
        return livreService.rechercher(auteur);
    }

    @GET @Path ("/{id}")
    public LivreResponse getLivreById(@PathParam("id") Long id) {
        return livreService.getLivreById(id);
    }

    @POST 
    public Response createLivre(@Valid LivreRequest livreRequest, @Context UriInfo uriInfo) {
        LivreResponse livreResponse = livreService.createLivre(livreRequest);
    
        URI location = uriInfo.getAbsolutePathBuilder()
            .path(String.valueOf(livreResponse.id()))
            .build();

        return Response.created(location)
            .entity(livreResponse)
            .build();
    }
}

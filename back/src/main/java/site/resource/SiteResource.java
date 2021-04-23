package site.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import site.model.Personne;
import site.model.Site;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

@Singleton
@Path("site")
@Api(value = "site")
public class SiteResource {

    private final Site site;

    public SiteResource() {
        super();
        final ObjectMapper mapper = new ObjectMapper();
        Site site = null;
        try {
            site = mapper.readValue(new File("src/main/resources/site.json"), Site.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.site = site;
    }

    @GET
    @Path("pers/{email}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Personne getPersonne(@PathParam("email") final String email, @PathParam("password") final String password){
        final List<Personne> listPersonnes = site.getListCompte();
        final Personne res = listPersonnes
                .stream()
                .filter(m -> (m.getEmail().equals(email) && m.getPassword().equals(password)))
                .findFirst()
                .orElse(null);
        if (res == null) throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST).build());
        return res;
    }

    @POST
    @Path("compte/{firstname}/{lastname}/{email}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Personne postPersonne(@PathParam("firstname") final String firstname,
                                 @PathParam("lastname") final String lastname,
                                 @PathParam("email") final String email,
                                 @PathParam("password") final String password){
        final Personne personne = new Personne(firstname,lastname);
        personne.setEmail(email);
        personne.setPassword(password);
        try {
            site.addPersonne(personne);
        }catch(final IllegalArgumentException ex) {
            throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST, ex.getMessage()).build());
        }
        return personne;
    }

}

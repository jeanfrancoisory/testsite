package site.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.awt.SystemTray;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.List;

@Singleton
@Path("site")
@Api(value = "site")
public class SiteResource {

    private final Site site;

    public SiteResource() {
        super();
        System.out.println("cc");
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
    public Response getPersonne(@PathParam("email") final String email, @PathParam("password") final String password){
        final List<Personne> listPersonnes = site.getListCompte();
        final Personne res = listPersonnes
                .stream()
                .filter(m -> (m.getEmail().equals(email) && m.getPassword().equals(password)))
                .findFirst()
                .orElse(null);
        if (res == null) {
            System.out.println("not ok");
            throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST).build());
            //return Response
            //        .status(Response.Status.BAD_REQUEST)
            //        .build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(res)
                .build();
    }

    @POST
    @Path("compte/{firstname}/{lastname}/{email}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPersonne(@PathParam("firstname") final String firstname,
                                 @PathParam("lastname") final String lastname,
                                 @PathParam("email") final String email,
                                 @PathParam("password") final String password){
        final Personne personne = new Personne();
        personne.setFirst_name(firstname);
        personne.setLast_name(lastname);
        personne.setEmail(email);
        personne.setPassword(password);
        try {
            if(site.checkEmail(personne)) {
                throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_CONFLICT, "Email already taken").build());
            }
            site.addPersonne(personne);
        }catch(final IllegalArgumentException ex) {
            throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST, ex.getMessage()).build());
        }
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final String json = mapper.writeValueAsString(site);
            final PrintWriter writer = new PrintWriter("src/main/resources/site.json");
            writer.print(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response
                .status(Response.Status.OK)
                .entity(personne)
                .build();
    }

}

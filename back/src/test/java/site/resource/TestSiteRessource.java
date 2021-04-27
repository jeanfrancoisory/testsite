package site.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hanleyt.JerseyExtension;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import site.model.Personne;
import site.model.Site;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestSiteRessource {

    public String data;
    static {
        System.setProperty("jersey.config.test.container.port", "0");
    }

    //static final Logger log = Logger.getLogger(TestGameResource.class.getSimpleName());

    @SuppressWarnings("unused")
    @RegisterExtension
    JerseyExtension jerseyExtension = new JerseyExtension(this::configureJersey);

    Application configureJersey() {
        return new ResourceConfig(SiteResource.class)
                .register(MyExceptionMapper.class)
                .register(JacksonFeature.class);
    }

    @BeforeEach
    public void beforeEach() {
        try {
            data = Files.readString(Paths.get("src/main/resources/site.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void afterEach() {
        try {
            PrintWriter writer = new PrintWriter("src/main/resources/site.json");
            writer.print("");
            writer.close();
            FileWriter file = new FileWriter("src/main/resources/site.json");
            file.write(data);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    <T> T LogJSONAndUnmarshallValue(final Response res, final Class<T> classToRead) {
        res.bufferEntity();
        final String json = res.readEntity(String.class);
        //log.log(Level.INFO, "JSON received: " + json);
        final T obj = res.readEntity(classToRead);
        res.close();
        return obj;
    }

    @Test
    void testGetPersonne(final Client client, final URI baseUri) {
        final ObjectMapper mapper = new ObjectMapper();
        Site site = null;
        try {
            site = mapper.readValue(new File("src/main/resources/site.json"), Site.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Response responseAfterGet = client
                .target(baseUri)
                .path("site/pers/email/password")
                .request()
                .get();
        assertEquals(Response.Status.OK.getStatusCode(), responseAfterGet.getStatus());
        Personne personneAfterGet = responseAfterGet.readEntity(new GenericType<Personne>() {
        });
        assertEquals(personneAfterGet.getEmail(),"email");
    }

    @Test
    void testGetPersonne2(final Client client, final URI baseUri) {
        final ObjectMapper mapper = new ObjectMapper();
        Site site = null;
        try {
            site = mapper.readValue(new File("src/main/resources/site.json"), Site.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Response responseAfterGet = client
                .target(baseUri)
                .path("site/pers/emai/password")
                .request()
                .get();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), responseAfterGet.getStatus());
    }
}

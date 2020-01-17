package feature;


import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import model.Tournament;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/Tournament")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public interface Tournamentfeature {



    /**
     *  Funktion, die alle Tournament sucht
     * @return Turniertplan nach Tournament
     */
    @GET
    @Timed
    @UnitOfWork
    List<Tournament> getAllTournament();


    /**
     * Gibt der Turniertplan zurück
     * @param beginDate Anfangsdatum des Turrniertplans
     * @param bisdate Endsdatum des Turrniertplans
     * @return ein Turniertplan
     */
    @GET
    @Path("/findbyDate")
    @UnitOfWork
   List<Tournament> getTournamentbyDate(@QueryParam("VonDate") Optional<String> beginDate, @QueryParam("BisDate") Optional<String> bisdate);


    @POST
    @UnitOfWork
    Tournament addTournament(@Valid Tournament t);


    /**
     * Funktion, die einen Turniertplan in der DB Ändert
      * @param d Die Klasse Turniertplan
     * @return Geänderter Turniertplan
     */
    @PUT
    @Path("/{id}")
    @Timed
    @UnitOfWork
    Tournament updateTournament(@QueryParam("id") Optional<Integer> id, Tournament d);

    /**
     * Löscht einen Turniertplan aus der Datenbank
     * @param id Turniert wird gelöscht
     * @param t E-Mail des zu löschenden Spielers
     */
    @DELETE
    @Timed
    @Path("/{id}")
    @UnitOfWork
    void deleteTournament(@QueryParam("TourneyplanID") int id, Tournament t);



}

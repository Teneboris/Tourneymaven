package model;

import feature.Tournamentfeature;
import persistence.DataStorage;

import javax.persistence.NoResultException;
import javax.ws.rs.*;
import java.util.*;

public class TournamentServiceResource implements Tournamentfeature {

    private DataStorage spielerDAO;

    public TournamentServiceResource(DataStorage spielerDAO) {

        this.spielerDAO = spielerDAO;

    }

    @Override
    public List<Tournament> getAllTournament() {
        return spielerDAO.getAllTournameName();
    }

    @Override
    public List<Tournament> getTournamentbyDate(Optional<String> beginDate, Optional<String> bisdate) {

        if(beginDate.isPresent()&&bisdate.isPresent())
        {
            try
            {
                return spielerDAO.findTournamenbyDate(beginDate.get(),bisdate.get());
            }
            catch(NoResultException e)
            {
                throw new WebApplicationException("Turnier nicht gefunden", 420);
            }
        }
        else
        {
            throw new WebApplicationException("Keine Parameter bekommen", 421);
        }
    }


    @Override
    public Tournament addTournament(Tournament t) {

            try {
                Tournament erg = spielerDAO.insert(t);
                 erg.setId(t.getId());
                 return erg;
            } catch (NoResultException e)  //Wenn nichts gefunden, einfügen
            {

            }
          throw new WebApplicationException("Turnier schon Vorhanden", 422);
    }

    @Override
    public Tournament updateTournament(Optional<Integer> id, Tournament t) {
        boolean b = spielerDAO.exists(Tournament.class, "id", t.getId());
        if(b)
        {
            try
            {
               // spielerDAO.findbyID(id.get());
                spielerDAO.update(t);
                return t;
            }
            catch(NoResultException e)
            {
                throw new WebApplicationException("VonDate nicht gefunden", 420);
            }
        }
        else
        {
            throw new WebApplicationException("Keine Parameter bekommen", 421);
        }
    }


    @Override
    public void deleteTournament(int id, Tournament t) {
        boolean b = spielerDAO.exists(Tournament.class, "id", t.getId());
        if(b)
        {
            try
            {
                spielerDAO.delete(t);
            }
            catch(NoResultException e)
            {
                throw new WebApplicationException("Turnier nicht gefunden", 420);
            }
        }
        else
        {
            throw new WebApplicationException("Keine link bekommen", 421);
        }
    }





}

package persistence;


import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.*;


import model.Tournament;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public class DataStorage extends AbstractDAO<Tournament> {


    public DataStorage(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }

    /**
            Gibt alle Turniert aus der DB zurück
     */

    public List<Tournament> getAllTournameName()
    {

     return list(namedQuery("findtAllTournameName"));

    }

    /**
     * Gibt ein Turniert zurück die zu einem Turniertplan gehören
     * @param vondate Anfangsdatum des Turnierts
     * @param bisdate Endsdatum des Turnierts
     * @return Liste von Spielern die angegebener Trainer betreut
     */
    public List<Tournament> findTournamenbyDate(String vondate, String bisdate)
    {
        return list(namedQuery("src.main.model.findTournamentbyDate").setParameter("VonDate", vondate).setParameter("BisDate" ,bisdate));
    }



    public Tournament findbyID(int id)
    {
        //return list(namedQuery("src.main.model.findbyID").setParameter("TourneyplanID", id));
        return (Tournament) currentSession().get(Tournament.class, id);
    }
    /**
     * Löscht angegebenen Turniert aus der DB
     * @param Tour zu loeschender Spieler
     */

    public void delete(Tournament Tour)
    {
        currentSession().delete(Tour);
    }


    /**
     * Ändert angegebenen Turniert
     * @param tour zu updatender Turniert
     */
    public void update(Tournament tour)
    {
        currentSession().saveOrUpdate(tour);

    }

    /**
     * fügt neuen Turniert hinzu
     * @param tour Neuer Turniert
     * @return
     */
    public Tournament insert(Tournament tour)
    {
       return persist(tour);
    }

    /**
     * @param clazz Klasse der Turniertplan Also Torunament
     * @param idKey ID der Turniert im DB
     * @param idValue Object der Turniert
     * @return gibt true zurück wenn die id gefunden wurde
     */

    public boolean exists(Class clazz, String idKey ,Object idValue) {
        Session session = currentSession();
        return session.createCriteria(clazz)
                .add(Restrictions.eq(idKey, idValue))
                .setProjection(Projections.property(idKey))
                .uniqueResult() != null;


    }


}

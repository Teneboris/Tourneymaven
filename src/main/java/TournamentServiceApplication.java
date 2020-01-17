

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import model.Tournament;
import model.TournamentServiceResource;
import persistence.DataStorage;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class TournamentServiceApplication extends Application<Tournamentserviceconfiguration>{


    public static void main(String... args)throws Exception {
        new TournamentServiceApplication().run(args);

    }

    /**
     * Hibernate bundle.
     */
    private final HibernateBundle<Tournamentserviceconfiguration> hibernateBundle
            = new HibernateBundle<Tournamentserviceconfiguration>( Tournament.class)
    {
        @Override
        public DataSourceFactory getDataSourceFactory(Tournamentserviceconfiguration configuration)
        {
            return configuration.getDataSourceFactory();
        }
    };


    @Override
    public void initialize(Bootstrap<Tournamentserviceconfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(Tournamentserviceconfiguration tournamentserviceconfiguration, Environment environment) throws Exception {

        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // DO NOT pass a preflight request to down-stream auth filters
        // unauthenticated preflight requests should be permitted by spec
        cors.setInitParameter(CrossOriginFilter.CHAIN_PREFLIGHT_PARAM, Boolean.FALSE.toString());

        final DataStorage spielerDAO = new DataStorage(hibernateBundle.getSessionFactory());

       final TournamentServiceResource tournamentServiceResource = new TournamentServiceResource(spielerDAO);
        environment.jersey().register(tournamentServiceResource);
        //environment.jersey().register(new tournamentServiceResource(dbi.onDemand(PartsService.class)));
    }

}

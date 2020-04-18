package onlineexam;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import onlineexam.dao.UserDao;
import onlineexam.model.User;
import onlineexam.resources.UsersResource;

public class App extends Application<AppConfig> {
    public HibernateBundle<AppConfig> hibernateBundle = new HibernateBundle<AppConfig>(User.class) {

        public DataSourceFactory getDataSourceFactory(AppConfig config) {
            return config.getDataSourceFactory();
        }
    };

    public void initialize(
            final Bootstrap<AppConfig> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    public void run(AppConfig appConfig, Environment environment) {
        final UserDao userDao = new UserDao(hibernateBundle.getSessionFactory());


        final UsersResource usersResource = new UsersResource(userDao);


        environment.jersey().register(usersResource);
        environment.healthChecks().register("template", new OnlineexamCheck(appConfig.getVersion()));
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}

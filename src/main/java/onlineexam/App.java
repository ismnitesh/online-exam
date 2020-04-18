package onlineexam;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import onlineexam.resources.UsersResource;

public class App extends Application<AppConfig> {

    public void run(AppConfig appConfig, Environment environment) {
        final UsersResource usersResource = new UsersResource();
        environment.jersey().register(usersResource);
        environment.healthChecks().register("template", new OnlineexamCheck(appConfig.getVersion()));
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}

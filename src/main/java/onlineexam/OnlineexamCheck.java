package onlineexam;

import com.codahale.metrics.health.HealthCheck;

public class OnlineexamCheck extends HealthCheck {
    private final String version;

    public OnlineexamCheck(String version) {
        this.version = version;
    }

    protected Result check() throws Exception {
        return Result.healthy("OK with version: "+this.version);
    }
}

package com.nuwit.schedule;

import com.nuwit.schedule.api.ScheduleHealthCheck;
import com.nuwit.schedule.api.ScheduleResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ScheduleApplication extends Application<ScheduleConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ScheduleApplication().run(args);
    }

    @Override
    public String getName() {
        return "schedule-api";
    }

    @Override
    public void initialize(final Bootstrap<ScheduleConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ScheduleConfiguration configuration,
                    final Environment environment) {
        final ScheduleResource scheduleResource = new ScheduleResource();
        final ScheduleHealthCheck healthCheck =
                new ScheduleHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(scheduleResource);
    }

}

package com.yammer.maze.solver;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.maze.solver.resources.LabEscapeResource;

public class LabEscapeService extends Service<LabEscapeConfiguration> {

    public static void main(String[] args) throws Exception { new LabEscapeService().run(args); }

    public void initialize(Bootstrap<LabEscapeConfiguration> bootstrap) {}

    public void run(LabEscapeConfiguration labEscapeConfiguration, Environment environment) throws Exception {
        environment.addResource(new LabEscapeResource());
    }
}

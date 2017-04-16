package com.twbarber

import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import io.dropwizard.configuration.EnvironmentVariableSubstitutor
import io.dropwizard.configuration.SubstitutingSourceProvider



class HelloWorldApplication: Application<HelloWorldConfiguration>() {

    companion object {
        @Throws(Exception::class)
        @JvmStatic fun main(args: Array<String>) {
            HelloWorldApplication().run(*args)
        }
    }

    override fun getName(): String {
        return "hello-world"
    }

    override fun initialize(bootstrap: Bootstrap<HelloWorldConfiguration>) {
        bootstrap.configurationSourceProvider = SubstitutingSourceProvider(
                bootstrap.configurationSourceProvider,
                EnvironmentVariableSubstitutor(false)
        )
    }

    override fun run(configuration: HelloWorldConfiguration, environment: Environment) {
        val resource = HelloWorldResource(configuration.template, configuration.defaultName)
        val healthCheck = TemplateHealthCheck(configuration.template)

        environment.healthChecks().register("template", healthCheck)
        environment.jersey().register(resource)
    }
}
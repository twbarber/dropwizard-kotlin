package com.twbarber

import com.codahale.metrics.health.HealthCheck


class TemplateHealthCheck(private val template: String) : HealthCheck() {

    @Throws(Exception::class)
    override fun check(): HealthCheck.Result {
        val saying = String.format(template, "TEST")
        if (!saying.contains("TEST")) {
            return HealthCheck.Result.unhealthy("template doesn't include a name")
        }
        return HealthCheck.Result.healthy()
    }

}
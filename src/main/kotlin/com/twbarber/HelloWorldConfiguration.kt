package com.twbarber

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration

class HelloWorldConfiguration: Configuration() {
    @JsonProperty("template")
    var template: String = ""

    @JsonProperty("defaultName")
    var defaultName: String = "Stranger"
}

package de.sambalmueslie.eventorg.game.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.eventorg.common.CrudObjectChangeRequest

@JsonIgnoreProperties(ignoreUnknown = true)
data class NationChangeRequest(
    @JsonProperty
    val name: String,
) : CrudObjectChangeRequest

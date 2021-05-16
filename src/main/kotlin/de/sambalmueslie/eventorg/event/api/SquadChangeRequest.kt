package de.sambalmueslie.eventorg.event.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.eventorg.common.CrudObject
import de.sambalmueslie.eventorg.common.CrudObjectChangeRequest

@JsonIgnoreProperties(ignoreUnknown = true)
data class SquadChangeRequest(
    @JsonProperty
    val teamId: Long,
    @JsonProperty
    val unitId: Long,
) : CrudObjectChangeRequest

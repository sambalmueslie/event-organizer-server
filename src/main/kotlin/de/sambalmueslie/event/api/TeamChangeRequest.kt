package de.sambalmueslie.event.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.common.CrudObjectChangeRequest

@JsonIgnoreProperties(ignoreUnknown = true)
data class TeamChangeRequest(
    @JsonProperty
    val clanId: Long,
    @JsonProperty
    val nationId: Long,
    @JsonProperty
    val eventId: Long,
) : CrudObjectChangeRequest

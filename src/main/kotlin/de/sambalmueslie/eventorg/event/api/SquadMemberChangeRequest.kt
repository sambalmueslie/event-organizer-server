package de.sambalmueslie.eventorg.event.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.eventorg.common.CrudObjectChangeRequest

@JsonIgnoreProperties(ignoreUnknown = true)
data class SquadMemberChangeRequest(
    @JsonProperty
    val playerId: Long,
    @JsonProperty
    val unitSlotId: Long,
) : CrudObjectChangeRequest

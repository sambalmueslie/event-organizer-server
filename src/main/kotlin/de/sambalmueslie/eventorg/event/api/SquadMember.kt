package de.sambalmueslie.eventorg.event.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.eventorg.common.CrudObject

@JsonIgnoreProperties(ignoreUnknown = true)
data class SquadMember(
    @JsonProperty
    override val id: Long,
    @JsonProperty
    val playerId: Long,
    @JsonProperty
    val unitSlotId: Long,
) : CrudObject

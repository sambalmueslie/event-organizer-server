package de.sambalmueslie.eventorg.event.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.eventorg.common.CrudObject

@JsonIgnoreProperties(ignoreUnknown = true)
data class Team(
    @JsonProperty
    override val id: Long,
    @JsonProperty
    val clanId: Long,
    @JsonProperty
    val nationId: Long,
    @JsonProperty
    val eventId: Long
) : CrudObject

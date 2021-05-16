package de.sambalmueslie.eventorg.event.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.eventorg.common.CrudObject

@JsonIgnoreProperties(ignoreUnknown = true)
data class Squad(
    @JsonProperty
    override val id: Long,
    @JsonProperty
    val teamId: Long,
    @JsonProperty
    val unitId: Long,
) : CrudObject

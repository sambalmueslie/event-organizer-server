package de.sambalmueslie.eventorg.game.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.eventorg.common.CrudObject

@JsonIgnoreProperties(ignoreUnknown = true)
data class Nation(
    @JsonProperty
    override val id: Long,
    @JsonProperty
    val name: String
): CrudObject

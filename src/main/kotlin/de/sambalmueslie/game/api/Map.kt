package de.sambalmueslie.game.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.common.CrudObject

@JsonIgnoreProperties(ignoreUnknown = true)
data class Map(
    @JsonProperty
    override val id: Long,
    @JsonProperty
    val name: String
) : CrudObject

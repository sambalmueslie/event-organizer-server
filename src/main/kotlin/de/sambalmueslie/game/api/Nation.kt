package de.sambalmueslie.game.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Nation(
    @JsonProperty
    val id: Long,
    @JsonProperty
    val name: String
)

package de.sambalmueslie.eventorg.game.api


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.eventorg.common.CrudObject

@JsonIgnoreProperties(ignoreUnknown = true)
data class UnitSlot(
    @JsonProperty
    override val id: Long,
    @JsonProperty
    val text: String,
    @JsonProperty
    val leader: Boolean,
    @JsonProperty
    val unitId: Long
) : CrudObject

package de.sambalmueslie.game.api


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.common.CrudObject
import de.sambalmueslie.common.CrudObjectChangeRequest

@JsonIgnoreProperties(ignoreUnknown = true)
data class UnitSlotChangeRequest(
    @JsonProperty
    val text: String,
    @JsonProperty
    val leader: Boolean,
    @JsonProperty
    val unitId: Long
) : CrudObjectChangeRequest

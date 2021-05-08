package de.sambalmueslie.game.api


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.common.CrudObjectChangeRequest

@JsonIgnoreProperties(ignoreUnknown = true)
data class UnitChangeRequest(
    @JsonProperty
    val type: UnitType,
    @JsonProperty
    val size: Int,
    @JsonProperty
    val nationId: Long
) : CrudObjectChangeRequest

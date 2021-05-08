package de.sambalmueslie.eventorg.game.api


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.eventorg.common.CrudObject

@JsonIgnoreProperties(ignoreUnknown = true)
data class Unit(
	@JsonProperty
	override val id: Long,
	@JsonProperty
	val type: UnitType,
	@JsonProperty
	val size: Int,
	@JsonProperty
	val nationId: Long
) : CrudObject

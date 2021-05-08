package de.sambalmueslie.game.api


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.common.CrudObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory

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

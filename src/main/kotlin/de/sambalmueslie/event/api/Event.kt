package de.sambalmueslie.event.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.common.CrudObject
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
data class Event(
    @JsonProperty
    override val id: Long,
    @JsonProperty
    val title: String,
    @JsonProperty
    val description: String,
    @JsonProperty
    val timestamp: LocalDateTime,
    @JsonProperty
    val rounds: Int,
    @JsonProperty
    val mapId: Long,
) : CrudObject

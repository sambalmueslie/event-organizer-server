package de.sambalmueslie.event.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.common.CrudObjectChangeRequest
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
data class EventChangeRequest(
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
) : CrudObjectChangeRequest

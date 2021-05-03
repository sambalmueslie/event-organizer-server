package de.sambalmueslie.clan.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.common.CrudObjectChangeRequest

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlayerChangeRequest(
    @JsonProperty
    val name: String = "",
    @JsonProperty
    val email: String = "",
    @JsonProperty
    val steamId: String = "",
    @JsonProperty
    val discordId: String = ""
) : CrudObjectChangeRequest

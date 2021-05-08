package de.sambalmueslie.eventorg.clan.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.eventorg.common.CrudObject

@JsonIgnoreProperties(ignoreUnknown = true)
data class Player(
    @JsonProperty
    override val id: Long,
    @JsonProperty
    var name: String = "",
    @JsonProperty
    var email: String = "",
    @JsonProperty
    var steamId: String = "",
    @JsonProperty
    var discordId: String = ""
) : CrudObject

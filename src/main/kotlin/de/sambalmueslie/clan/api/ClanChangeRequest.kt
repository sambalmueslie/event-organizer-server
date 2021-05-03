package de.sambalmueslie.clan.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.common.CrudObjectChangeRequest
import javax.persistence.Column

@JsonIgnoreProperties(ignoreUnknown = true)
data class ClanChangeRequest(
    @JsonProperty
    val name: String = "",
    @JsonProperty
    val tag: String = "",
    @JsonProperty
    val discordId: String = ""
) : CrudObjectChangeRequest

package de.sambalmueslie.clan.api


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import de.sambalmueslie.common.CrudObject
@JsonIgnoreProperties(ignoreUnknown = true)
data class Clan(
    @JsonProperty
    override val id: Long,
    @JsonProperty
    val name: String = "",
    @JsonProperty
    val tag: String = "",
    @JsonProperty
    val discordId: String = ""
) : CrudObject

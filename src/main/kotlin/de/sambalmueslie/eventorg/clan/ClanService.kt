package de.sambalmueslie.eventorg.clan

import de.sambalmueslie.eventorg.clan.api.Clan
import de.sambalmueslie.eventorg.clan.api.ClanChangeRequest
import de.sambalmueslie.eventorg.clan.db.ClanData
import de.sambalmueslie.eventorg.clan.db.ClanRepository
import de.sambalmueslie.eventorg.common.BaseCrudService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class ClanService(private val repo: ClanRepository) : BaseCrudService<Clan, ClanChangeRequest, ClanData>(repo, logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ClanService::class.java)
    }

    override fun convert(request: ClanChangeRequest) = ClanData.convert(request)

    fun findByDiscordId(discordId: String): Clan? = repo.findByDiscordId(discordId)?.convert()

}

package de.sambalmueslie.clan

import de.sambalmueslie.clan.api.Clan
import de.sambalmueslie.clan.api.ClanChangeRequest
import de.sambalmueslie.clan.db.ClanData
import de.sambalmueslie.clan.db.ClanRepository
import de.sambalmueslie.common.BaseCrudService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class ClanService(private val repo: ClanRepository) : BaseCrudService<Clan, ClanChangeRequest, ClanData>(repo, logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ClanService::class.java)
    }

    override fun convert(request: ClanChangeRequest) = ClanData.convert(request)


}

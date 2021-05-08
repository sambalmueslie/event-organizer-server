package de.sambalmueslie.eventorg.game


import de.sambalmueslie.eventorg.common.BaseCrudService
import de.sambalmueslie.eventorg.game.api.Nation
import de.sambalmueslie.eventorg.game.api.NationChangeRequest
import de.sambalmueslie.eventorg.game.db.NationData
import de.sambalmueslie.eventorg.game.db.NationRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class NationService(private val repo: NationRepository) : BaseCrudService<Nation, NationChangeRequest, NationData>(repo, logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(NationService::class.java)
    }

    override fun convert(request: NationChangeRequest) = NationData.convert(request)
}

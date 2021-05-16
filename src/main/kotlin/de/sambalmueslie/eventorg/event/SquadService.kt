package de.sambalmueslie.eventorg.event


import de.sambalmueslie.eventorg.common.BaseCrudService
import de.sambalmueslie.eventorg.event.api.Squad
import de.sambalmueslie.eventorg.event.api.SquadChangeRequest
import de.sambalmueslie.eventorg.event.api.Team
import de.sambalmueslie.eventorg.event.api.TeamChangeRequest
import de.sambalmueslie.eventorg.event.db.SquadData
import de.sambalmueslie.eventorg.event.db.SquadRepository
import de.sambalmueslie.eventorg.event.db.TeamData
import de.sambalmueslie.eventorg.event.db.TeamRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class SquadService(private val repo: SquadRepository) : BaseCrudService<Squad, SquadChangeRequest, SquadData>(repo, logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(SquadService::class.java)
    }

    override fun convert(request: SquadChangeRequest) = SquadData.convert(request)
}

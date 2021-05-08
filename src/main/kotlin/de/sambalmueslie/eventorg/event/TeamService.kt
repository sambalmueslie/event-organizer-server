package de.sambalmueslie.eventorg.event


import de.sambalmueslie.eventorg.common.BaseCrudService
import de.sambalmueslie.eventorg.event.api.Team
import de.sambalmueslie.eventorg.event.api.TeamChangeRequest
import de.sambalmueslie.eventorg.event.db.TeamData
import de.sambalmueslie.eventorg.event.db.TeamRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class TeamService(private val repo: TeamRepository) : BaseCrudService<Team, TeamChangeRequest, TeamData>(repo, logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(TeamService::class.java)
    }

    override fun convert(request: TeamChangeRequest) = TeamData.convert(request)
}

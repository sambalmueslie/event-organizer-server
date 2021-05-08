package de.sambalmueslie.event


import de.sambalmueslie.common.BaseCrudService
import de.sambalmueslie.event.api.Team
import de.sambalmueslie.event.api.TeamChangeRequest
import de.sambalmueslie.event.db.TeamData
import de.sambalmueslie.event.db.TeamRepository
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

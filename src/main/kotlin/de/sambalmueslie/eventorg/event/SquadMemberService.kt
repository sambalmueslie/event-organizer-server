package de.sambalmueslie.eventorg.event


import de.sambalmueslie.eventorg.common.BaseCrudService
import de.sambalmueslie.eventorg.event.api.*
import de.sambalmueslie.eventorg.event.db.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class SquadMemberService(private val repo: SquadMemberRepository) : BaseCrudService<SquadMember, SquadMemberChangeRequest, SquadMemberData>(repo, logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(SquadMemberService::class.java)
    }

    override fun convert(request: SquadMemberChangeRequest) = SquadMemberData.convert(request)
}

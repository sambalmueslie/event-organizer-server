package de.sambalmueslie.event


import de.sambalmueslie.clan.api.ClanChangeRequest
import de.sambalmueslie.clan.db.ClanData
import de.sambalmueslie.common.BaseCrudService
import de.sambalmueslie.event.api.Event
import de.sambalmueslie.event.api.EventChangeRequest
import de.sambalmueslie.event.db.EventData
import de.sambalmueslie.event.db.EventRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class EventService(private val repo: EventRepository) : BaseCrudService<Event, EventChangeRequest, EventData>(repo, logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(EventService::class.java)
    }

    override fun convert(request: EventChangeRequest) = EventData.convert(request)
}

package de.sambalmueslie.eventorg.event


import de.sambalmueslie.eventorg.common.BaseCrudService
import de.sambalmueslie.eventorg.event.api.Event
import de.sambalmueslie.eventorg.event.api.EventChangeRequest
import de.sambalmueslie.eventorg.event.db.EventData
import de.sambalmueslie.eventorg.event.db.EventRepository
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

package de.sambalmueslie.eventorg.event.api

import de.sambalmueslie.eventorg.common.CrudAPI

interface EventAPI : CrudAPI<Event, EventChangeRequest> {
}

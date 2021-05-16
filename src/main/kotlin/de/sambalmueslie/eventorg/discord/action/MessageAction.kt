package de.sambalmueslie.eventorg.discord.action

import discord4j.core.event.domain.message.MessageCreateEvent

interface MessageAction {
    fun matches(event: MessageCreateEvent): Boolean
    suspend fun handleMessage(event: MessageCreateEvent)
}

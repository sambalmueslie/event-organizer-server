package de.sambalmueslie.eventorg.discord


import de.sambalmueslie.eventorg.clan.ClanService
import de.sambalmueslie.eventorg.clan.PlayerService
import de.sambalmueslie.eventorg.config.DiscordConfig
import de.sambalmueslie.eventorg.discord.action.CreateMessageAction
import de.sambalmueslie.eventorg.discord.action.HelpMessageAction
import de.sambalmueslie.eventorg.discord.action.SetupMessageAction
import de.sambalmueslie.eventorg.event.EventService
import discord4j.core.DiscordClient
import discord4j.core.event.domain.guild.GuildEvent
import discord4j.core.event.domain.message.MessageCreateEvent
import io.micronaut.context.annotation.Context
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.mono
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Context
class DiscordService(
    clanService: ClanService,
    playerService: PlayerService,
    eventService: EventService,
    config: DiscordConfig
) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(DiscordService::class.java)
    }

    private val messageActions = listOf(
        HelpMessageAction(),
        SetupMessageAction(clanService, playerService, config),
        CreateMessageAction(clanService, eventService, config)
    )

    init {
        val client = DiscordClient.create(config.token)
        client.withGateway { mono { it.on(MessageCreateEvent::class.java).asFlow().collect { handleMessage(it) } } }.block()
        client.withGateway { mono { it.on(GuildEvent::class.java).asFlow().collect { handleGuildEvent(it) } } }.block()
    }

    private fun handleGuildEvent(event: GuildEvent) {
        // TODO not implemented yet
    }

    private suspend fun handleMessage(event: MessageCreateEvent) {
        messageActions.filter { it.matches(event) }.forEach { it.handleMessage(event) }
    }

}

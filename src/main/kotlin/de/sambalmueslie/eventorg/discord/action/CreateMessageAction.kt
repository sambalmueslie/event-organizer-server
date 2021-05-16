package de.sambalmueslie.eventorg.discord.action


import de.sambalmueslie.eventorg.config.DiscordConfig
import discord4j.core.event.domain.message.MessageCreateEvent
import kotlinx.coroutines.reactor.awaitSingle
import org.slf4j.Logger
import org.slf4j.LoggerFactory

internal class CreateMessageAction(private val config: DiscordConfig) : BaseMessageAction(config, logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(CreateMessageAction::class.java)
    }

    override fun matches(event: MessageCreateEvent): Boolean {
        return event.message.content.startsWith("!create")
    }

    override suspend fun handleMessage(event: MessageCreateEvent) {
        logger.info("Create event channel")
        val guild = event.guild.awaitSingle()
        val adminChannel = getAdminChannel(guild)
        val msgChannel = event.message.channel.awaitSingle()
        if (adminChannel == null) {
            msgChannel.createMessage("Event Organizer Bot is not correctly setup - please run !setup").awaitSingle()
            return
        }
        if (adminChannel.id != event.message.channelId) {
            msgChannel.createMessage("Invalid command channel, only '${config.adminChannelName}' is allowed to run this command").awaitSingle()
            return
        }
        val title = event.message.content.substringAfter(' ')
        if (title.isBlank()) {
            msgChannel.createMessage("Invalid event channel title '$title'").awaitSingle()
            return
        }

        val eventCategory = getEventCategory(guild)
        if (eventCategory == null) {
            msgChannel.createMessage("Event Organizer Bot is not correctly setup - please run !setup").awaitSingle()
            return
        }

        val eventChannel = createRestrictedTextChannel(guild, title, event.message.author, eventCategory.id)
    }

}

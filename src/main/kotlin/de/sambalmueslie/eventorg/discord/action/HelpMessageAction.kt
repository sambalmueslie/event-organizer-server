package de.sambalmueslie.eventorg.discord.action


import discord4j.core.event.domain.message.MessageCreateEvent
import discord4j.rest.util.Color
import kotlinx.coroutines.reactor.awaitSingle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Instant

internal class HelpMessageAction : MessageAction {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(HelpMessageAction::class.java)
    }

    override fun matches(event: MessageCreateEvent): Boolean {
        return event.message.content.startsWith("!help")
    }

    override suspend fun handleMessage(event: MessageCreateEvent) {
        val channel = event.message.channel.awaitSingle()
        channel.createEmbed {
            it.setColor(Color.RED)
                .setAuthor("How to use event organizer", "", "")
                .setTitle("Event Organizer Help")
                .setDescription("Event organizer helps you to organize your hell let loose clan event")
                .addField("!help", "Show help info", false)
                .addField("!setup", "Integrate your clan with event organizier bot", false)
                .addField("!create %s", "Create an event dont forget the name", false)
                .setTimestamp(Instant.now())
        }.awaitSingle()
    }


}

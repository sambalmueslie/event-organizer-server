package de.sambalmueslie.eventorg.discord.action


import de.sambalmueslie.eventorg.clan.ClanService
import de.sambalmueslie.eventorg.clan.PlayerService
import de.sambalmueslie.eventorg.clan.api.ClanChangeRequest
import de.sambalmueslie.eventorg.config.DiscordConfig
import de.sambalmueslie.eventorg.discord.DiscordService
import discord4j.core.`object`.PermissionOverwrite
import discord4j.core.`object`.entity.Guild
import discord4j.core.`object`.entity.channel.TextChannel
import discord4j.core.event.domain.message.MessageCreateEvent
import discord4j.rest.util.PermissionSet
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactor.awaitSingle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono

internal class SetupMessageAction(
    private val clanService: ClanService,
    private val playerService: PlayerService,
    private val config: DiscordConfig
) : BaseMessageAction(config, logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(SetupMessageAction::class.java)
    }

    override fun matches(event: MessageCreateEvent): Boolean {
        return event.message.content.startsWith("!setup")
    }

    override suspend fun handleMessage(event: MessageCreateEvent) {
        val guild = event.guild.awaitSingle()
        DiscordService.logger.info("Setup bot for ${guild.id}")
        getAdminChannel(guild) ?: createAdminChannel(guild, event)
        getEventCategory(guild) ?: createEventCategory(guild, event)
        importData(guild)
    }


    private suspend fun createAdminChannel(guild: Guild, event: MessageCreateEvent): TextChannel {
        DiscordService.logger.info("Create admin channel")
        // TODO ask for admin role and add admin too
        return createRestrictedTextChannel(guild, config.adminChannelName, event.message.author)
    }


    private suspend fun createEventCategory(guild: Guild, event: MessageCreateEvent) {
        logger.info("Create event category channel")
        val everyoneRole = guild.everyoneRole.awaitSingle()
        val botMember = guild.members.filterWhen { m -> Mono.just(m.displayName == "EventOrganizer") }.awaitSingle()
        guild.createCategory {
            it.setName(config.eventCategoryName)
            it.setPermissionOverwrites(
                setOf(
                    PermissionOverwrite.forMember(event.message.author.get().id, PermissionSet.all(), PermissionSet.none()),
                    PermissionOverwrite.forMember(botMember.id, PermissionSet.all(), PermissionSet.none()),
                    PermissionOverwrite.forRole(everyoneRole.id, PermissionSet.none(), PermissionSet.all()),
                )
            )
        }.awaitSingle()
    }


    private fun importData(guild: Guild) {
        val clan = clanService.findByDiscordId(guild.id.asString()) ?: clanService.create(ClanChangeRequest(guild.name, "", guild.id.asString()))
//         TODO("Not yet implemented")
    }

}

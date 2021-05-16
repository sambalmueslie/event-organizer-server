package de.sambalmueslie.eventorg.discord.action


import de.sambalmueslie.eventorg.config.DiscordConfig
import de.sambalmueslie.eventorg.discord.DiscordService
import discord4j.common.util.Snowflake
import discord4j.core.`object`.PermissionOverwrite
import discord4j.core.`object`.entity.Guild
import discord4j.core.`object`.entity.User
import discord4j.core.`object`.entity.channel.Channel
import discord4j.core.`object`.entity.channel.TextChannel
import discord4j.rest.util.PermissionSet
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactor.awaitSingle
import org.slf4j.Logger
import reactor.core.publisher.Mono
import java.util.*

abstract class BaseMessageAction(private val config: DiscordConfig, private val logger: Logger) : MessageAction {

    protected suspend fun createRestrictedTextChannel(guild: Guild, name: String, author: Optional<User>, parentId: Snowflake? = null): TextChannel {
        DiscordService.logger.info("Create restricted text channel '$name'")
        // TODO ask for admin role and add admin too
        val everyoneRole = guild.everyoneRole.awaitSingle()
        val botMember = guild.members.filterWhen { m -> Mono.just(m.displayName == "EventOrganizer") }.awaitSingle()
        return guild.createTextChannel {
            it.setName(name)
            it.setParentId(parentId)
            it.setPermissionOverwrites(
                setOf(
                    PermissionOverwrite.forMember(author.get().id, PermissionSet.all(), PermissionSet.none()),
                    PermissionOverwrite.forMember(botMember.id, PermissionSet.all(), PermissionSet.none()),
                    PermissionOverwrite.forRole(everyoneRole.id, PermissionSet.none(), PermissionSet.all()),
                )
            )
        }.awaitSingle()
    }

    protected suspend fun getAdminChannel(guild: Guild) = guild.channels.filter { it.name == config.adminChannelName }.awaitFirstOrNull()
    protected suspend fun getEventCategory(guild: Guild) = guild.channels.filter { it.type == Channel.Type.GUILD_CATEGORY }
        .filter { it.name == config.eventCategoryName }.awaitFirstOrNull()
}

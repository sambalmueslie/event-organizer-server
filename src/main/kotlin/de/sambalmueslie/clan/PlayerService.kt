package de.sambalmueslie.clan


import de.sambalmueslie.clan.api.Player
import de.sambalmueslie.clan.api.PlayerChangeRequest
import de.sambalmueslie.clan.db.PlayerData
import de.sambalmueslie.clan.db.PlayerRepository
import de.sambalmueslie.common.BaseCrudService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class PlayerService(private val repo: PlayerRepository) : BaseCrudService<Player, PlayerChangeRequest, PlayerData>(repo, logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(PlayerService::class.java)
    }

    override fun convert(request: PlayerChangeRequest) = PlayerData.convert(request)

}

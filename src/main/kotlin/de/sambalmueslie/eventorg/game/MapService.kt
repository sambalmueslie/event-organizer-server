package de.sambalmueslie.eventorg.game


import de.sambalmueslie.eventorg.common.BaseCrudService
import de.sambalmueslie.eventorg.game.api.MapChangeRequest
import de.sambalmueslie.eventorg.game.api.Map
import de.sambalmueslie.eventorg.game.db.MapData
import de.sambalmueslie.eventorg.game.db.MapRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class MapService(private val repo: MapRepository) : BaseCrudService<Map, MapChangeRequest, MapData>(repo, logger) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(MapService::class.java)
    }

    override fun convert(request: MapChangeRequest) = MapData.convert(request)
}

package de.sambalmueslie.game


import de.sambalmueslie.common.BaseCrudService
import de.sambalmueslie.game.api.Map
import de.sambalmueslie.game.api.MapChangeRequest
import de.sambalmueslie.game.db.MapData
import de.sambalmueslie.game.db.MapRepository
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

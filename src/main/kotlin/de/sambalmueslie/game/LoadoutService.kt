package de.sambalmueslie.game


import de.sambalmueslie.common.BaseCrudService
import de.sambalmueslie.game.api.Loadout
import de.sambalmueslie.game.api.LoadoutChangeRequest
import de.sambalmueslie.game.api.Map
import de.sambalmueslie.game.api.MapChangeRequest
import de.sambalmueslie.game.db.LoadoutData
import de.sambalmueslie.game.db.LoadoutRepository
import de.sambalmueslie.game.db.MapData
import de.sambalmueslie.game.db.MapRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class LoadoutService(private val repo: LoadoutRepository) : BaseCrudService<Loadout, LoadoutChangeRequest, LoadoutData>(repo, logger) {

	companion object {
		val logger: Logger = LoggerFactory.getLogger(LoadoutService::class.java)
	}

	override fun convert(request: LoadoutChangeRequest) = LoadoutData.convert(request)
}

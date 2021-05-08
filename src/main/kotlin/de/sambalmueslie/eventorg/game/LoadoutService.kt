package de.sambalmueslie.eventorg.game


import de.sambalmueslie.eventorg.common.BaseCrudService
import de.sambalmueslie.eventorg.game.api.Loadout
import de.sambalmueslie.eventorg.game.api.LoadoutChangeRequest
import de.sambalmueslie.eventorg.game.db.LoadoutData
import de.sambalmueslie.eventorg.game.db.LoadoutRepository
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

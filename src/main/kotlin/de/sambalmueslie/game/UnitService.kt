package de.sambalmueslie.game


import de.sambalmueslie.common.BaseCrudService
import de.sambalmueslie.game.api.Unit
import de.sambalmueslie.game.api.UnitChangeRequest
import de.sambalmueslie.game.api.Map
import de.sambalmueslie.game.api.MapChangeRequest
import de.sambalmueslie.game.db.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class UnitService(private val repo: UnitRepository) : BaseCrudService<Unit, UnitChangeRequest, UnitData>(repo, logger) {

	companion object {
		val logger: Logger = LoggerFactory.getLogger(UnitService::class.java)
	}

	override fun convert(request: UnitChangeRequest) = UnitData.convert(request)
}

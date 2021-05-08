package de.sambalmueslie.eventorg.game


import de.sambalmueslie.eventorg.common.BaseCrudService
import de.sambalmueslie.eventorg.game.api.Unit
import de.sambalmueslie.eventorg.game.api.UnitChangeRequest
import de.sambalmueslie.eventorg.game.db.UnitData
import de.sambalmueslie.eventorg.game.db.UnitRepository
import de.sambalmueslie.eventorg.game.db.*
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

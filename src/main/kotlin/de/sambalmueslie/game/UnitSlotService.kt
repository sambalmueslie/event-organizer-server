package de.sambalmueslie.game


import de.sambalmueslie.common.BaseCrudService
import de.sambalmueslie.game.api.UnitSlot
import de.sambalmueslie.game.api.UnitSlotChangeRequest
import de.sambalmueslie.game.api.Map
import de.sambalmueslie.game.api.MapChangeRequest
import de.sambalmueslie.game.db.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class UnitSlotService(private val repo: UnitSlotRepository) : BaseCrudService<UnitSlot, UnitSlotChangeRequest, UnitSlotData>(repo, logger) {

	companion object {
		val logger: Logger = LoggerFactory.getLogger(UnitSlotService::class.java)
	}

	override fun convert(request: UnitSlotChangeRequest) = UnitSlotData.convert(request)
}

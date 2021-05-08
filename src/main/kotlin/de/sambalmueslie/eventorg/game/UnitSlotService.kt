package de.sambalmueslie.eventorg.game


import de.sambalmueslie.eventorg.common.BaseCrudService
import de.sambalmueslie.eventorg.game.api.UnitSlot
import de.sambalmueslie.eventorg.game.api.UnitSlotChangeRequest
import de.sambalmueslie.eventorg.game.db.*
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

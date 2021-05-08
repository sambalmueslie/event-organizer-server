package de.sambalmueslie.game

import de.sambalmueslie.clan.api.Player
import de.sambalmueslie.game.api.*
import io.micronaut.data.model.Pageable
import io.micronaut.security.authentication.Authentication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class UnitSlotServiceTest {
    @Inject
    lateinit var nationService: NationService

    @Inject
    lateinit var unitService: UnitService

    @Inject
    lateinit var service: UnitSlotService

    private val auth: Authentication = mockk()

    @Test
    fun `crud for unit slot`() {
        val text = "Unit Name"
        val leader = true
        val nation = nationService.create(auth, NationChangeRequest("Test"))!!
        val unit = unitService.create(auth, UnitChangeRequest(UnitType.INFANTERIE, 1, nation.id))!!

        var unitSlot = service.create(auth, UnitSlotChangeRequest(text, leader, unit.id))
        assertNotNull(unitSlot)
        val reference = UnitSlot(unitSlot!!.id, text, leader, unit.id)
        assertEquals(reference, unitSlot)

        unitSlot = service.get(auth, unitSlot.id)
        assertEquals(reference, unitSlot)

        assertEquals(listOf(reference), service.getAll(auth, Pageable.from(0)).content)

        val update = UnitSlotChangeRequest("NewName", false, unit.id)
        unitSlot = service.update(auth, unitSlot!!.id, update)

        assertEquals(UnitSlot(unitSlot!!.id, update.text, update.leader, update.unitId), unitSlot)

        service.delete(auth, unitSlot.id)

        assertEquals(emptyList<UnitSlot>(), service.getAll(auth, Pageable.from(0)).content)
    }
}

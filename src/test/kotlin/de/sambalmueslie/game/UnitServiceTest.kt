package de.sambalmueslie.game

import de.sambalmueslie.clan.api.PlayerChangeRequest
import de.sambalmueslie.game.api.NationChangeRequest
import de.sambalmueslie.game.api.Unit
import de.sambalmueslie.game.api.UnitChangeRequest
import de.sambalmueslie.game.api.UnitType
import io.micronaut.data.model.Pageable
import io.micronaut.security.authentication.Authentication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class UnitServiceTest {
    @Inject
    lateinit var nationService: NationService

    @Inject
    lateinit var service: UnitService

    private val auth: Authentication = mockk()

    @Test
    fun `crud for player`() {
        val nation = nationService.create(auth, NationChangeRequest("Test"))!!
        val type = UnitType.INFANTERIE
        val size = 6

        var unit = service.create(auth, UnitChangeRequest(type, size, nation.id))
        assertNotNull(unit)
        val reference = Unit(unit!!.id, type, size, nation.id)
        assertEquals(reference, unit)

        unit = service.get(auth, unit.id)
        assertEquals(reference, unit)

        assertEquals(listOf(reference), service.getAll(auth, Pageable.from(0)).content)

        val update = UnitChangeRequest(UnitType.ARMOR, 3, nation.id)
        unit = service.update(auth, unit!!.id, update)

        assertEquals(Unit(unit!!.id, update.type, update.size, update.nationId), unit)

        service.delete(auth, unit.id)

        assertEquals(emptyList<Unit>(), service.getAll(auth, Pageable.from(0)).content)
    }
}

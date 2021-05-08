package de.sambalmueslie.event

import de.sambalmueslie.clan.ClanService
import de.sambalmueslie.clan.api.ClanChangeRequest
import de.sambalmueslie.event.api.EventChangeRequest
import de.sambalmueslie.event.api.Team
import de.sambalmueslie.event.api.TeamChangeRequest
import de.sambalmueslie.game.MapService
import de.sambalmueslie.game.NationService
import de.sambalmueslie.game.api.Loadout
import de.sambalmueslie.game.api.LoadoutChangeRequest
import de.sambalmueslie.game.api.MapChangeRequest
import de.sambalmueslie.game.api.NationChangeRequest
import io.micronaut.data.model.Pageable
import io.micronaut.security.authentication.Authentication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import javax.inject.Inject

@MicronautTest
internal class TeamServiceTest {
    @Inject
    lateinit var clanService: ClanService
    @Inject
    lateinit var eventService: EventService
    @Inject
    lateinit var nationService: NationService
    @Inject
    lateinit var mapService: MapService
    @Inject
    lateinit var service: TeamService

    private val auth: Authentication = mockk()

    @Test
    fun `crud for loadout`() {
        val clan = clanService.create(auth, ClanChangeRequest("test", "", ""))!!
        val nation = nationService.create(auth, NationChangeRequest("Test"))!!
        val map = mapService.create(auth, MapChangeRequest("Test"))!!
        val event = eventService.create(auth, EventChangeRequest("Test", "", LocalDateTime.now(), 1,map.id))!!

        var team = service.create(auth, TeamChangeRequest(clan.id, nation.id, event.id))
        assertNotNull(team)
        val reference = Team(team!!.id, clan.id, nation.id, event.id)
        assertEquals(reference, team)

        team = service.get(auth, team.id)
        assertEquals(reference, team)

        assertEquals(listOf(reference), service.getAll(auth, Pageable.from(0)).content)

        val update = TeamChangeRequest(clan.id, nation.id, event.id)
        team = service.update(auth, team!!.id, update)

        assertEquals(Team(team!!.id, update.clanId, update.nationId, update.eventId), team)

        service.delete(auth, team.id)

        assertEquals(emptyList<Team>(), service.getAll(auth, Pageable.from(0)).content)
    }
}

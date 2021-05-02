package de.sambalmueslie.clan.db

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Suppress("JpaMissingIdInspection")
@Entity(name = "ClanMember")
@Table(name = "clan_member")
data class ClanMemberData(
    @Column
    var clanId: Long = 0,
    @Column
    var playerId: Long = 0,
)

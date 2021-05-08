package de.sambalmueslie.game.db

import javax.persistence.*

@Entity(name = "Map")
@Table(name = "map")
data class MapData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(nullable = false, unique = true)
    var name: String = "",
)

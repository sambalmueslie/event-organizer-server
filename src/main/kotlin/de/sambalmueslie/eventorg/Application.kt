package de.sambalmueslie.eventorg

import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("de.sambalmueslie.eventorg")
        .start()
}


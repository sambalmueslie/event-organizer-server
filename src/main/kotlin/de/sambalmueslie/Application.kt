package de.sambalmueslie

import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("de.sambalmueslie")
        .start()
}


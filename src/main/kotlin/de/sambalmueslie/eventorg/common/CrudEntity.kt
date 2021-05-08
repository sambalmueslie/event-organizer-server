package de.sambalmueslie.eventorg.common

interface CrudEntity<T : CrudObject, R : CrudObjectChangeRequest> {
    fun convert(): T
    fun update(request: R)
}

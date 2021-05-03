package de.sambalmueslie.common

interface CrudEntity<T : CrudObject, R : CrudObjectChangeRequest> {
    fun convert(): T
    fun update(request: R)
}

package de.sambalmueslie.eventorg.common

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.security.authentication.Authentication

interface CrudAPI<T : CrudObject, R : CrudObjectChangeRequest> {
    fun create(auth: Authentication, request: R): T?
    fun update(auth: Authentication, id: Long, request: R): T?
    fun get(auth: Authentication, id: Long): T?
    fun getAll(auth: Authentication, pageable: Pageable): Page<T>
    fun delete(auth: Authentication, id: Long)
}

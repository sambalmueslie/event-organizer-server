package de.sambalmueslie.common


import de.sambalmueslie.clan.ClanService
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.data.repository.PageableRepository
import io.micronaut.security.authentication.Authentication
import org.slf4j.Logger

abstract class BaseCrudService<T : CrudObject, R : CrudObjectChangeRequest, D : CrudEntity<T, R>>(
    private val repo: PageableRepository<D, Long>,
    private val logger: Logger
) : CrudAPI<T, R> {

    override fun create(auth: Authentication, request: R): T? {
        logger.debug("Create $request")
        // TODO validate request
        val data = convert(request)
        return repo.save(data).convert()
    }

    override fun update(auth: Authentication, id: Long, request: R): T? {
        logger.debug("Update [$id] $request")
        // TODO validate request
        val data = repo.findByIdOrNull(id) ?: return create(auth, request)
        data.update(request)
        return repo.update(data).convert()
    }

    override fun get(auth: Authentication, id: Long): T? {
        return repo.findByIdOrNull(id)?.convert()
    }

    override fun getAll(auth: Authentication, pageable: Pageable): Page<T> {
        return repo.findAll(pageable).map { it.convert() }
    }

    override fun delete(auth: Authentication, id: Long) {
        logger.debug("Delete [$id]")
        repo.deleteById(id)
    }

    protected abstract fun convert(request: R): D


}

package com.igorivkin.website.service

import com.igorivkin.website.exception.EntityDoesNotExistException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.function.Function
import javax.transaction.Transactional

abstract class BaseServiceImpl<EntityT, IdT> constructor(
    private val repository: JpaRepository<EntityT, IdT>,
    private val entityTypeClass: Class<EntityT>?
) : BaseService<EntityT, IdT> {


    override fun create(entity: EntityT): EntityT {
        if (entity == null) {
            throw IllegalArgumentException("Cannot create an entity of type $entityTypeClass, provided entity is null")
        }
        return repository.save(entity)
    }

    override fun update(id: IdT, entityFromUpdate: EntityT): EntityT {
        return update(id, entityFromUpdate, null)
    }

    @Transactional
    override fun update(id: IdT, entityFromUpdate: EntityT, mappingCallback: Function<EntityT, EntityT>?): EntityT {
        var entityToUpdate: EntityT = loadForUpdateById(id)
        if (mappingCallback != null) {
            entityToUpdate = mappingCallback.apply(entityToUpdate)
        }
        if (entityToUpdate != null) {
            if (entityFromUpdate != null) {
                val mappedEntity = mapBeforeUpdate(entityToUpdate, entityFromUpdate)
                if (mappedEntity != null) {
                    return repository.save(mappedEntity)
                } else {
                    throw IllegalArgumentException("Cannot update the entity of class $entityTypeClass because mapBeforeUpdate returned null")
                }
            } else {
                throw IllegalArgumentException("Cannot update the entity of class $entityTypeClass because provided entity is null")
            }
        } else {
            throw IllegalArgumentException("Cannot update the entity of class $entityTypeClass by id $id, entity is null")
        }
    }

    override fun findAll(): List<EntityT> {
        return repository.findAll()
    }

    override fun findAll(pageable: Pageable): Page<EntityT> {
        return repository.findAll(pageable)
    }

    override fun findById(id: IdT): EntityT {
        if (id == null) {
            throw IllegalArgumentException("Cannot get entity of type $entityTypeClass by ID, provided ID is null")
        }
        return repository.findById(id)
            .orElseThrow { EntityDoesNotExistException("Cannot find entity of type $entityTypeClass by ID - $id") }
    }

    open fun loadForUpdateById(id: IdT): EntityT {
        return findById(id)
    }

    open fun mapBeforeUpdate(updateTo: EntityT, updateFrom: EntityT): EntityT {
        return updateFrom
    }

    override fun delete(entity: EntityT) {
        if (entity == null) {
            throw IllegalArgumentException("Cannot delete an entity of type $entityTypeClass, provided entity is null")
        }
        repository.delete(entity)
    }

    override fun deleteById(id: IdT) {
        if (id == null) {
            throw IllegalArgumentException("Cannot delete entity of type $entityTypeClass by ID, provided ID is null")
        }
        repository.deleteById(id)
    }

    override fun deleteAll() {
        repository.deleteAll()
    }

    override fun count(): Long {
        return repository.count()
    }
}

package com.igorivkin.website.service

import com.igorivkin.website.exception.EntityDoesNotExistException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.function.BiFunction
import org.springframework.data.jpa.repository.JpaRepository

abstract class BaseServiceImpl<EntityT, IdT, DtoT> constructor(
    private val repository: JpaRepository<EntityT, IdT>,
    private val entityTypeClass: Class<EntityT>?,
    private val dtoTypeClass: Class<DtoT>?,
): BaseService<EntityT, IdT, DtoT> {


    override fun create(entity: EntityT): EntityT {
        if(entity == null) {
            throw IllegalArgumentException("Cannot create an entity of type $entityTypeClass, provided entity is null")
        }
        return repository.save(entity)
    }

    override fun createFromDto(dto: DtoT): EntityT {
        val entityToSave: EntityT = fromDto(dto)
        return create(entityToSave)
    }

    override fun updateFromDto(id: IdT, dto: DtoT): EntityT {
        return updateFromDto(id, dto, null)
    }

    override fun updateFromDto(id: IdT, dto: DtoT, mappingCallback: BiFunction<EntityT, DtoT, EntityT>?): EntityT {
        var entityToUpdate: EntityT = findById(id)
        entityToUpdate = if(mappingCallback == null) {
            fromDto(dto)
        } else {
            mappingCallback.apply(entityToUpdate, dto)
        }
        if(entityToUpdate != null) {
            return repository.save(entityToUpdate)
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
        if(id == null) {
            throw IllegalArgumentException("Cannot get entity of type $entityTypeClass by ID, provided ID is null")
        }
        return repository.findById(id).orElseThrow { EntityDoesNotExistException("Cannot find entity of type $entityTypeClass by ID - $id") }
    }

    override fun delete(entity: EntityT) {
        if(entity == null) {
            throw IllegalArgumentException("Cannot delete an entity of type $entityTypeClass, provided entity is null")
        }
        repository.delete(entity)
    }

    override fun deleteById(id: IdT) {
        if(id == null) {
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

    abstract fun fromDto(dto: DtoT): EntityT
    abstract fun toDto(entity: EntityT): DtoT

    override fun toListOfDto(entityList: List<EntityT>): List<DtoT> {
        return entityList.map { entity -> this.toDto(entity) }.toList()
    }
}

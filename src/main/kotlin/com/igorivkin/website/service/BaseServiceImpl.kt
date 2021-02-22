package com.igorivkin.website.service

import java.util.function.BiFunction
import org.springframework.data.jpa.repository.JpaRepository

abstract class BaseServiceImpl<EntityT, IdT, DtoT> constructor(
    private val repository: JpaRepository<EntityT, IdT>,
    private val entityTypeClass: Class<EntityT>?,
    private val dtoTypeClass: Class<DtoT>?,
    ) : BaseService<EntityT, IdT, DtoT> {

    override fun findAll(): List<EntityT>? {
        return repository.findAll()
    }

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
        TODO("Not yet implemented")
    }

    override fun updateFromDto(id: IdT, dto: DtoT, mappingCallback: BiFunction<EntityT, DtoT, EntityT>?): EntityT {
        TODO("Not yet implemented")
    }

    override fun findById(id: IdT): EntityT {
        if(id == null) {
            throw IllegalArgumentException("Cannot get entity of type $entityTypeClass by ID, provided ID is null")
        }
        return repository.findById(id).orElseThrow { IllegalArgumentException("Cannot find entity of type $entityTypeClass by ID - $id") }
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

    override fun convertToDto(entity: EntityT): DtoT {
        TODO("Not yet implemented")
    }

    override fun convertToDto(listOfEntities: List<EntityT>?): List<DtoT>? {
        TODO("Not yet implemented")
    }

    abstract fun fromDto(dto: DtoT): EntityT
}

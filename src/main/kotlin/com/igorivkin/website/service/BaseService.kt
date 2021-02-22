package com.igorivkin.website.service

import java.util.function.BiFunction

interface BaseService<EntityT, IdT, DtoT> {
    fun findAll(): List<EntityT>?
    fun create(entity: EntityT): EntityT
    fun createFromDto(dto: DtoT): EntityT
    fun updateFromDto(id: IdT, dto: DtoT): EntityT
    fun updateFromDto(id: IdT, dto: DtoT, mappingCallback: BiFunction<EntityT, DtoT, EntityT>?): EntityT
    fun findById(id: IdT): EntityT
    fun delete(entity: EntityT)
    fun deleteById(id: IdT)
    fun deleteAll()
    fun count(): Long

    // DTO specific methods
    fun convertToDto(entity: EntityT): DtoT
    fun convertToDto(listOfEntities: List<EntityT>?): List<DtoT>?
}

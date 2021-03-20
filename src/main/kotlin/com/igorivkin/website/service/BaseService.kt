package com.igorivkin.website.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.function.BiFunction

interface BaseService<EntityT, IdT, DtoT> {
    fun create(entity: EntityT): EntityT
    fun createFromDto(dto: DtoT): EntityT

    fun findAll(): List<EntityT>
    fun findAll(pageable: Pageable): Page<EntityT>
    fun findById(id: IdT): EntityT
    fun count(): Long

    fun updateFromDto(id: IdT, dto: DtoT): EntityT
    fun updateFromDto(id: IdT, dto: DtoT, mappingCallback: BiFunction<EntityT, DtoT, EntityT>?): EntityT

    fun delete(entity: EntityT)
    fun deleteById(id: IdT)
    fun deleteAll()

    fun toListOfDto(entityList: List<EntityT>): List<DtoT>
}

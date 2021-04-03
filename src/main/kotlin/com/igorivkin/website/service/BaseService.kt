package com.igorivkin.website.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.function.Function

interface BaseService<EntityT, IdT> {
    fun create(entity: EntityT): EntityT

    fun findAll(): List<EntityT>
    fun findAll(pageable: Pageable): Page<EntityT>
    fun findById(id: IdT): EntityT
    fun count(): Long

    fun update(id: IdT, entityFromUpdate: EntityT): EntityT
    fun update(id: IdT, entityFromUpdate: EntityT, mappingCallback: Function<EntityT, EntityT>?): EntityT

    fun delete(entity: EntityT)
    fun deleteById(id: IdT)
    fun deleteAll()
}

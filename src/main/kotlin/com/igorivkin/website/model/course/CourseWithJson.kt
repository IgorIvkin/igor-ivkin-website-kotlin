package com.igorivkin.website.model.course

import com.igorivkin.website.model.BaseModel
import com.vladmihalcea.hibernate.type.array.IntArrayType
import com.vladmihalcea.hibernate.type.array.StringArrayType
import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType
import com.vladmihalcea.hibernate.type.json.JsonNodeStringType
import com.vladmihalcea.hibernate.type.json.JsonStringType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import org.hibernate.validator.constraints.Length
import javax.persistence.Column
import javax.persistence.Entity
import javax.validation.constraints.NotNull

//@Entity(name = "courses")
@TypeDefs(value = [
    TypeDef(name = "string-array", typeClass = StringArrayType::class),
    TypeDef(name = "int-array", typeClass = IntArrayType::class),
    TypeDef(name = "json", typeClass = JsonStringType::class),
    TypeDef(name = "jsonb", typeClass = JsonBinaryType::class),
    TypeDef(name = "jsonb-node", typeClass = JsonNodeBinaryType::class),
    TypeDef(name = "json-node", typeClass = JsonNodeStringType::class)
])
class CourseWithJson(

    @NotNull
    @Length(min = 1, max = 255)
    var title: String,

    @NotNull
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    var parts: CourseStructure

): BaseModel<Long>()
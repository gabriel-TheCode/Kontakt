package com.gabrielthecode.kontakt.data.network.mapper

interface EntityMapper<Entity, DomainModel> {
	fun mapToDomain(entity: Entity): DomainModel
	fun mapToEntity(domainModel: DomainModel): Entity
}

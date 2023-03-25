package com.github.linzhengen.domain.product

import java.util.UUID

interface Repository {
    fun findOne(id: UUID): Product
}
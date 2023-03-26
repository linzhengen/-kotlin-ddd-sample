package com.github.linzhengen.infrastructure.user.persistence.mysql

import com.github.linzhengen.domain.user.User
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

object Users : UUIDTable("users") {
    val name: Column<String> = varchar("name", 50)
}

object UserDao {
    fun create(name: String) = transaction {
        Users.insertAndGetId { it[this.name] = name }.value
    }

    fun delete(id: UUID) = transaction {
        Users.deleteWhere { Users.id eq id }
    }

    fun find(id: UUID) = transaction {
        Users.select { Users.id eq id }.single().let {
            User(it[Users.id].value, it[Users.name])
        }
    }
}
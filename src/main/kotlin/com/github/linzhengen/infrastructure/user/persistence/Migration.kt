package com.github.linzhengen.infrastructure.user.persistence

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Schema
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class Migration {
    companion object {
        val database = Database.connect("jdbc:sqlite:test.db", "org.sqlite.JDBC")
        val tables = arrayOf(
            Users,
        ).also {
            transaction {
                SchemaUtils.create(*it)
            }
        }
    }
}
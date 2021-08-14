/*
 * Module: r2-lcp-kotlin
 * Developers: Aferdita Muriqi, Clément Baumann
 *
 * Copyright (c) 2019. Readium Foundation. All rights reserved.
 * Use of this source code is governed by a BSD-style license which is detailed in the
 * LICENSE file present in the project repository where this source code is maintained.
 */

package org.readium.r2.lcp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(
    entities = [Transactions::class, Licenses::class],
    version = 2,
    exportSchema = false
)
abstract class LcpDatabase : RoomDatabase() {

    abstract fun lcpDao(): LcpDao

    companion object {
        @Volatile
        private var INSTANCE: LcpDatabase? = null

        fun getDatabase(context: Context): LcpDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            val MIGRATION_1_2 = object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {

                }
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LcpDatabase::class.java,
                    "lcpdatabase"
                ).allowMainThreadQueries().addMigrations(MIGRATION_1_2).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

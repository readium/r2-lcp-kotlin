/*
 * Module: r2-lcp-kotlin
 * Developers: Aferdita Muriqi
 *
 * Copyright (c) 2019. Readium Foundation. All rights reserved.
 * Use of this source code is governed by a BSD-style license which is detailed in the
 * LICENSE file present in the project repository where this source code is maintained.
 */

package org.readium.r2.lcp.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Transactions.TABLE_NAME)
data class Transactions(
    @ColumnInfo(name = ID)
    var id: String?,
    @ColumnInfo(name = ORIGIN)
    val origin: String?,
    @ColumnInfo(name = USERID)
    val userId: String?,
    @ColumnInfo(name = PASSPHRASE)
    val passphrase: String?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name =  PKID)
    var pkId: Long? = null
) {

    companion object {

        const val TABLE_NAME = "Transactions"
        const val ID = "id"
        const val PKID = "PKID"
        const val ORIGIN = "origin"
        const val USERID = "userId"
        const val PASSPHRASE = "passphrase"
    }
}

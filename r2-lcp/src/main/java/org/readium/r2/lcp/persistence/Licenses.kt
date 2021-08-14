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

@Entity(tableName = Licenses.TABLE_NAME)
data class Licenses(
    @ColumnInfo(name = ID)
    var id: String?,
    @ColumnInfo(name = PRINTSLEFT)
    val printsLeft: Int?,
    @ColumnInfo(name = COPIESLEFT)
    val copiesLeft: Int?,
    @ColumnInfo(name = REGISTERED)
    val registered: Boolean? = false,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PKID)
    var pkId: Long? = null
) {

    companion object {

        const val TABLE_NAME = "Licenses"
        const val ID = "id"
        const val PKID = "PKID"
        const val PRINTSLEFT = "printsLeft"
        const val COPIESLEFT = "copiesLeft"
        const val REGISTERED = "registered"
    }
}

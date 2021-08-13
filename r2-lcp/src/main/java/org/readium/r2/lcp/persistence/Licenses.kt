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
    @PrimaryKey
    @ColumnInfo(name = ID)
    var id: String,
    @ColumnInfo(name = PRINTSLEFT)
    val printsLeft: Int?,
    @ColumnInfo(name = COPIESLEFT)
    val copiesLeft: Int?,
    @ColumnInfo(name = REGISTERED)
    val registered: Boolean? = false
) {

    companion object {

        const val TABLE_NAME = "LICENSES"
        const val ID = "ID"
        const val PRINTSLEFT = "PRINTSLEFT"
        const val COPIESLEFT = "COPIESLEFT"
        const val REGISTERED = "REGISTERED"
    }
}

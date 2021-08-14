package org.readium.r2.lcp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LcpDao {

    /**
     * Retrieve passphrase
     * @return Passphrase
     */
    @Query("SELECT ${Transactions.PASSPHRASE} FROM ${Transactions.TABLE_NAME} WHERE ${Transactions.ORIGIN} = :licenseId")
    suspend fun passphrase(licenseId: String): String?

    @Query("SELECT ${Transactions.PASSPHRASE} FROM ${Transactions.TABLE_NAME} WHERE ${Transactions.USERID} = :userId")
    suspend fun passphrases(userId: String): List<String>

    @Query("SELECT ${Transactions.PASSPHRASE} FROM ${Transactions.TABLE_NAME}")
    suspend fun allPassphrases(): List<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPassphrase(passphrase: Transactions)

    @Query("SELECT ${Licenses.ID} FROM ${Licenses.TABLE_NAME} WHERE ${Licenses.ID} = :licenseId")
    suspend fun exists(licenseId: String): String?

    @Query("SELECT ${Licenses.REGISTERED} FROM ${Licenses.TABLE_NAME} WHERE ${Licenses.ID} = :licenseId AND ${Licenses.REGISTERED} = 1")
    suspend fun isDeviceRegistered(licenseId: String): Boolean

    @Query("UPDATE ${Licenses.TABLE_NAME} SET ${Licenses.REGISTERED} = 1 WHERE ${Licenses.ID} = :licenseId")
    suspend fun registerDevice(licenseId: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLicense(license: Licenses)

    @Query("SELECT ${Licenses.COPIESLEFT} FROM ${Licenses.TABLE_NAME} WHERE ${Licenses.ID} = :licenseId")
    fun getCopiesLeft(licenseId: String): Int?

    @Query("UPDATE ${Licenses.TABLE_NAME} SET ${Licenses.COPIESLEFT} = :quantity WHERE ${Licenses.ID} = :licenseId")
    fun setCopiesLeft(quantity: Int, licenseId: String)

    @Query("SELECT ${Licenses.PRINTSLEFT} FROM ${Licenses.TABLE_NAME} WHERE ${Licenses.ID} = :licenseId")
    fun getPrintsLeft(licenseId: String): Int?

    @Query("UPDATE ${Licenses.TABLE_NAME} SET ${Licenses.PRINTSLEFT} = :quantity WHERE ${Licenses.ID} = :licenseId")
    fun setPrintsLeft(quantity: Int, licenseId: String)
}
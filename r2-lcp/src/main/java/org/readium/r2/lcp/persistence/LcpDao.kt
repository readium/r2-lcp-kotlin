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
    @Query("SELECT ${Passphrase.PASSPHRASE} FROM ${Passphrase.TABLE_NAME} WHERE ${Passphrase.PROVIDER} = :licenseId")
    suspend fun passphrase(licenseId: String): String?

    @Query("SELECT ${Passphrase.PASSPHRASE} FROM ${Passphrase.TABLE_NAME} WHERE ${Passphrase.USERID} = :userId")
    suspend fun passphrases(userId: String): List<String>

    @Query("SELECT ${Passphrase.PASSPHRASE} FROM ${Passphrase.TABLE_NAME}")
    suspend fun allPassphrases(): List<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPassphrase(passphrase: Passphrase)

    @Query("SELECT ${Licenses.LICENSE_ID} FROM ${Licenses.TABLE_NAME} WHERE ${Licenses.LICENSE_ID} = :licenseId")
    suspend fun exists(licenseId: String): String?

    @Query("SELECT ${Licenses.REGISTERED} FROM ${Licenses.TABLE_NAME} WHERE ${Licenses.LICENSE_ID} = :licenseId")
    suspend fun isDeviceRegistered(licenseId: String): Boolean

    @Query("UPDATE ${Licenses.TABLE_NAME} SET ${Licenses.REGISTERED} = 1 WHERE ${Licenses.LICENSE_ID} = :licenseId")
    suspend fun registerDevice(licenseId: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLicense(license: Licenses)

    @Query("SELECT ${Licenses.RIGHTCOPY} FROM ${Licenses.TABLE_NAME} WHERE ${Licenses.LICENSE_ID} = :licenseId")
    fun getCopiesLeft(licenseId: String): Int?

    @Query("UPDATE ${Licenses.TABLE_NAME} SET ${Licenses.RIGHTCOPY} = :quantity WHERE ${Licenses.LICENSE_ID} = :licenseId")
    fun setCopiesLeft(quantity: Int, licenseId: String)

    @Query("SELECT ${Licenses.RIGHTPRINT} FROM ${Licenses.TABLE_NAME} WHERE ${Licenses.LICENSE_ID} = :licenseId")
    fun getPrintsLeft(licenseId: String): Int?

    @Query("UPDATE ${Licenses.TABLE_NAME} SET ${Licenses.RIGHTPRINT} = :quantity WHERE ${Licenses.LICENSE_ID} = :licenseId")
    fun setPrintsLeft(quantity: Int, licenseId: String)
}
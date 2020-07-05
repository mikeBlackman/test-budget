package com.dynamiccontrols.myob.persistance

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dynamiccontrols.myob.model.Transaction

@Dao
interface TransactionDao {

    @Query("SELECT * FROM `transaction`")
    fun getTransactions(): LiveData<List<Transaction>>

    @Query("SELECT * FROM `transaction` WHERE categoryName LIKE :categoryName")
    fun getTransactionsForCategory(categoryName: String): List<Transaction>

    @Insert
    suspend fun insertTransactions(vararg transactions: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)
}
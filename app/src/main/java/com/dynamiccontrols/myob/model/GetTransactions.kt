package com.dynamiccontrols.myob.model

import androidx.lifecycle.LiveData
import com.dynamiccontrols.myob.persistance.CategoriesDao
import com.dynamiccontrols.myob.persistance.TransactionDao
import javax.inject.Inject

interface GetTransactions {
    fun transactions() : LiveData<List<Transaction>>

    fun getTransactionsForCategory(name : String) : List<Transaction>
}

class GetTransactionsImpl @Inject constructor(private val transactionDao: TransactionDao) :
    GetTransactions {

    override fun transactions(): LiveData<List<Transaction>> {
        return transactionDao.getTransactions()
    }

    override fun getTransactionsForCategory(name : String): List<Transaction> {
        return transactionDao.getTransactionsForCategory(name)
    }

}

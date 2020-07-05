package com.dynamiccontrols.myob.model

import com.dynamiccontrols.myob.network.CurrencyApi
import com.dynamiccontrols.myob.network.CurrencyApi.Companion.ACCESS_KEY
import com.dynamiccontrols.myob.persistance.TransactionDao
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

interface TransactionRecorder {
    suspend fun recordTransaction(amount: Double, categoryName: String)
}

class TransactionRecorderImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val currencyApi: CurrencyApi
) : TransactionRecorder {

    override suspend fun recordTransaction(amount: Double, categoryName: String) {
        coroutineScope {
            val result = async { currencyApi.getRate(ACCESS_KEY, "NZD", "USD", 1) }
            val currencyResponse = result.await().body()
            val rate = currencyResponse!!.quotes.USDNZD
            val usdAmount = amount * rate
            val transaction =
                Transaction(
                    amount,
                    usdAmount,
                    categoryName,
                    Calendar.getInstance().time,
                    Currency.NZD
                )
            transactionDao.insertTransactions(transaction)
        }
    }

}
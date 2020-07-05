package com.dynamiccontrols.myob.ui.transactions

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dynamiccontrols.myob.model.GetTransactions
import com.dynamiccontrols.myob.model.Transaction
import kotlinx.coroutines.ExperimentalCoroutinesApi

class TransactionsViewModel @ViewModelInject constructor(getTransactions: GetTransactions) :
    ViewModel() {

    @ExperimentalCoroutinesApi
    var transactionsLiveData: LiveData<List<Transaction>> = getTransactions.transactions()

}

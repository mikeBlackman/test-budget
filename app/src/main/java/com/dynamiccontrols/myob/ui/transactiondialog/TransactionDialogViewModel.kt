package com.dynamiccontrols.myob.ui.transactiondialog

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dynamiccontrols.myob.model.CategoryManager
import com.dynamiccontrols.myob.model.TransactionRecorder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.math.roundToInt

class TransactionDialogViewModel @ViewModelInject constructor(
    private val categoryManager: CategoryManager, private val transactionRecorder: TransactionRecorder
) : ViewModel() {

    val displayAmountInputError = MutableLiveData<String>()

    fun addTransactionButtonClicked(amount: String, categoryName: String) {
        displayAmountInputError.value = ""
        if (amount.isNullOrEmpty()) {
            displayAmountInputError.value = "Please enter a valid amount"
        }
        try {
            val formattedValue = (amount.toDouble() * 100).roundToInt() / 100.0
            if (formattedValue != 0.0) {
                CoroutineScope(Dispatchers.IO).launch {
                    transactionRecorder.recordTransaction(formattedValue, categoryName)
                }
            } else {
                displayAmountInputError.value = "Zero is not a valid amount"
            }
        } catch (e: Exception) {
            displayAmountInputError.value = "Please enter a valid amount"
        }
    }

}

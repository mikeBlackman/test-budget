package com.dynamiccontrols.myob.model

import androidx.annotation.ColorRes
import java.util.*

data class Transaction(var amount : Float, var category: Category, var date: Date, val currency: Currency)

data class Category(val name : String, @ColorRes var backgroundColor : Int, @ColorRes var textColor : Int)

data class Budget(val amount : Float, val category: Category)

enum class Currency {
    NZD, USD
}
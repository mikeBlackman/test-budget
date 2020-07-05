package com.dynamiccontrols.myob.model

import androidx.room.*
import java.util.*

@Entity
@TypeConverters(DateConverter::class, CurrencyConverter::class)
data class Transaction(
    var amount: Double,
    var usdAmount : Double,
    var categoryName: String,
    var date: Date,
    val currency: Currency
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Entity(primaryKeys = ["amount", "categoryName"])
data class Budget(
    var amount: Double,
    var categoryName: String
)

enum class Currency(val value : Int) {
    NZD(0), USD(1)
}

@Entity
class Category(
    @PrimaryKey
    var name: String,
    var backgroundColor: Int,
    var textColor: Int
)

class DateConverter {
    @TypeConverter
    fun toDate(dateLong : Long) : Date{
        return  Date(dateLong)
    }
    @TypeConverter
    fun fromDate(date : Date) : Long {
        return date.time
    }
}

class CurrencyConverter {
    @TypeConverter
    fun toCurrency(value: String) = enumValueOf<Currency>(value)
    @TypeConverter
    fun fromCurrency(value: Currency) = value.name
}


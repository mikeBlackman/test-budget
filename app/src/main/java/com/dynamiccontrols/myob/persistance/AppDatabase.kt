package com.dynamiccontrols.myob.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dynamiccontrols.myob.model.Budget
import com.dynamiccontrols.myob.model.Category
import com.dynamiccontrols.myob.model.Transaction

@Database(entities = [Transaction::class, Budget::class, Category::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun categoriesDao(): CategoriesDao
}
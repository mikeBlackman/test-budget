package com.dynamiccontrols.myob.di

import android.content.Context
import androidx.room.Room
import com.dynamiccontrols.myob.model.*
import com.dynamiccontrols.myob.persistance.AppDatabase
import com.dynamiccontrols.myob.persistance.CategoriesDao
import com.dynamiccontrols.myob.persistance.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class TransactionModule {

    @Singleton
    @Provides
    fun provideCategoryManager(categoryManagerImpl: CategoryManagerImpl): CategoryManager {
        return categoryManagerImpl
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "myob"
        ).build()
    }

    @Provides
    fun provideTransactionDao(database: AppDatabase): TransactionDao {
        return database.transactionDao()
    }

    @Provides
    fun provideCategoriesDao(database: AppDatabase): CategoriesDao {
        return database.categoriesDao()
    }

    @Provides
    fun provideTransactionRecorder(transactionRecorderImpl: TransactionRecorderImpl): TransactionRecorder {
        return transactionRecorderImpl
    }

    @Provides
    fun provideGetTransactions(getTransactionsImpl: GetTransactionsImpl) : GetTransactions {
        return getTransactionsImpl
    }

}

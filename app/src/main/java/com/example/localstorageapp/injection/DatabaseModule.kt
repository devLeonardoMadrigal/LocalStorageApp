package com.example.localstorageapp.injection

import android.content.Context
import androidx.room.Room
import com.example.localstorageapp.dao.ShoppingItemDAO
import com.example.localstorageapp.database.ShoppingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//return instance of database object and inject in DAO
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ShoppingDatabase =
        Room.databaseBuilder (
            context,
            klass= ShoppingDatabase::class.java,
            name= "shopping_db"
        ).build()

    @Provides
    fun provideDAO(db: ShoppingDatabase): ShoppingItemDAO = db.shoppingItemDao()
}
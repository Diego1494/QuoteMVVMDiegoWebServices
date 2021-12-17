package dev.cardoso.quotesmvvm.di

import android.content.Context
import androidx.annotation.RestrictTo
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import dev.cardoso.quotesmvvm.data.QuoteRepositoryImpl
import dev.cardoso.quotesmvvm.data.local.QuoteDB
import dev.cardoso.quotesmvvm.data.local.daos.QuoteDAO
import dev.cardoso.quotesmvvm.domain.QuoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideQuoteDao(quoteDB: QuoteDB): QuoteDAO {
        return quoteDB.quoteDao()
    }

    @Provides
    @Singleton
    fun provideGetDatabase(@ApplicationContext appContext: Context,
                           scope: CoroutineScope
    ): QuoteDB {
        return QuoteDB.getDatabase(appContext, scope)
    }

    @Singleton // Provide always the same instance
    @Provides
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

}
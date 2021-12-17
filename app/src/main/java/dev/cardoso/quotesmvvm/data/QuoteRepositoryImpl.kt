package dev.cardoso.quotesmvvm.data

import android.accounts.NetworkErrorException
import dev.cardoso.quotesmvvm.data.local.QuoteLocalDataSource
import dev.cardoso.quotesmvvm.data.local.daos.QuoteDAO
import dev.cardoso.quotesmvvm.data.model.AddQuoteResponse
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import dev.cardoso.quotesmvvm.data.remote.QuoteRemoteDataSource
import dev.cardoso.quotesmvvm.domain.QuoteRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject


 class QuoteRepositoryImpl @Inject constructor(quoteDAO: QuoteDAO, var localDataSource: QuoteLocalDataSource,
                                                       var remoteDataSource: QuoteRemoteDataSource
                                              ): QuoteRepository {

    override suspend  fun getQuotes(): Flow<List<QuoteModel>> {
        val remoteQuotes =
        try {
            remoteDataSource.getQuotes()
        } catch (ex: Exception) {
            when (ex) {
                is NetworkErrorException -> throw ex
                else -> null
            }
        }
        val quotes = ArrayList<QuoteModel>()
        if (remoteQuotes != null) {
            remoteQuotes.collect {
                it?.forEach { quoteModel->
                    quotes.add(quoteModel)
                }
            }
            localDataSource.insertAll(quotes)
        }
        return (flow { emit (quotes) })
    }

    override suspend fun getQuoteRandom(): Flow<QuoteModel> {
        return  localDataSource.getQuoteRandom()
    }

    override suspend fun getQuote(quoteId: Int): Flow<QuoteModel> {
           return localDataSource.getQuote(quoteId)
    }

    override suspend fun editQuote(quoteModel: QuoteModel, token: String): Flow<QuoteResponse?> {
            return  remoteDataSource.editQuote(quoteModel, token)
        }

    override suspend fun addQuote(quoteModel: QuoteModel, token: String): Flow<QuoteResponse?> {
        return remoteDataSource.addQuote( quoteModel,token)

    }

     /*override suspend fun getQuotes(): Flow<QuoteResponse?> {
         return remoteDataSource.getQuotes()
     }*/


     override suspend fun deleteQuote(token: String, id: Int): Flow<QuoteResponse?> {
         return remoteDataSource.deleteQuote( token , id)
     }


 }

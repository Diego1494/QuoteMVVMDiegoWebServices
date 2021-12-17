package dev.cardoso.quotesmvvm.domain.usecase


import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import dev.cardoso.quotesmvvm.domain.QuoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddQuoteUseCase @Inject constructor (val quoteRepositoryImpl: QuoteRepository){
    suspend fun addQuote( quoteModel: QuoteModel, token:String): Flow<QuoteResponse?>
    {
        return quoteRepositoryImpl.addQuote(quoteModel,token)
    }
}
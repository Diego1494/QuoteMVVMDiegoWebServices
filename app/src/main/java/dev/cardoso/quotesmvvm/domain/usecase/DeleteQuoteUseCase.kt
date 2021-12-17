package dev.cardoso.quotesmvvm.domain.usecase

import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import dev.cardoso.quotesmvvm.domain.QuoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteQuoteUseCase @Inject constructor (val quoteRepository: QuoteRepository){
    suspend fun deleteQuote(token:String, id:Int): Flow<QuoteResponse?> {
     return   quoteRepository.deleteQuote(token,id)
    }
}
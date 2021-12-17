package dev.cardoso.quotesmvvm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import dev.cardoso.quotesmvvm.domain.usecase.DeleteQuoteUseCase
import dev.cardoso.quotesmvvm.domain.usecase.GetQuotesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeleteQuoteViewModel  @Inject constructor(private val deleteQuoteUseCase: DeleteQuoteUseCase): ViewModel() {
    private val quoteResponseMutableStateFlow= MutableStateFlow(
        QuoteResponse(false, "", listOf()
        )
    )
    val quoteResponse: StateFlow<QuoteResponse> = quoteResponseMutableStateFlow

    fun deleteQuote(token:String, id:Int){
        viewModelScope.launch {
            val quoteResponse = deleteQuoteUseCase.deleteQuote(token, id).first()
                quoteResponse.let {
                    if (quoteResponse!=null){
                        quoteResponseMutableStateFlow.value = quoteResponse
                    }

                }

        }
    }
}
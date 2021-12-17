package dev.cardoso.quotesmvvm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import dev.cardoso.quotesmvvm.domain.usecase.GetQuotesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
/*
class ListQuoteViewModel @Inject constructor(private val getQuotesUseCase : GetQuotesUseCase) : ViewModel()  {
    private val quoteResponseMutableStateFlow = MutableStateFlow(
        QuoteResponse(false,message ="", data= listOf())
    )
    val quoteResponse : StateFlow<QuoteResponse> = quoteResponseMutableStateFlow

    init {

        viewModelScope.launch {
            val quoteResponse = getQuotesUseCase.getQuotes().first()
            quoteResponse.let{
                if(quoteResponse != null){
                    quoteResponseMutableStateFlow.value=quoteResponse
                }
            }
        }
    }

}*/
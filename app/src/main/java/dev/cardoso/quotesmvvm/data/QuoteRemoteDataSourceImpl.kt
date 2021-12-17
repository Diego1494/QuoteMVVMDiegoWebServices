package dev.cardoso.quotesmvvm.data


import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import dev.cardoso.quotesmvvm.data.remote.QuoteApi
import dev.cardoso.quotesmvvm.data.remote.QuoteRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Response
import javax.inject.Inject



 class QuoteRemoteDataSourceImpl @Inject constructor(private  val api:QuoteApi): QuoteRemoteDataSource  {

    override suspend fun getQuotes(): Flow<List<QuoteModel>?> {
        val response =  api.getQuotes()

        return (response.body().let {
            flow { emit(it) }
        })
    }

    override suspend fun editQuote(quoteModel: QuoteModel, token: String): Flow<QuoteResponse?> {
        return  responseToQuoteResponse(api.editQuote(token, quoteModel.id, quoteModel))
    }

   override suspend fun addQuote(quoteModel: QuoteModel, token: String): Flow<QuoteResponse?> {
        return responseToQuoteResponse( api.addQuote(token, quoteModel))

    }
     /*override suspend fun getQuotes(): Flow<QuoteResponse?> {

         return responseToQuoteResponse(api.getQuotes())

     }*/

     override suspend fun deleteQuote( token: String, id: Int): Flow<QuoteResponse?> {
         return api.deleteQuote(token = token , id=id)?.let {
             responseToQuoteResponse(
                 it )
         }
     }



    private fun responseToQuoteResponse(response: Response<QuoteResponse>): Flow<QuoteResponse?> {
        return (when (response.isSuccessful) {
            true -> {
                response.body().let {
                    flow {
                        if (it != null) {
                            emit(it)
                        }
                    }
                }
            }
            else -> {
                val jsonObject =
                    JSONTokener(response.errorBody()?.string()).nextValue() as JSONObject
                val quoteResponse = QuoteResponse(
                    success = false,
                    message = jsonObject.getString("message"), data = listOf()
                )
                flow { emit(quoteResponse) }
            }
        })

    }

}


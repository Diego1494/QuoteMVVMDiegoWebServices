package dev.cardoso.quotesmvvm.data.model

import android.webkit.ConsoleMessage
import com.google.gson.annotations.SerializedName
import io.reactivex.internal.operators.single.SingleDoOnSuccess

data class QuoteResponse (
    @SerializedName("succes")
    var success: Boolean,

    @SerializedName("message")
    var message: String,

    @SerializedName("data")
    var data: List<QuoteModel>,





    )
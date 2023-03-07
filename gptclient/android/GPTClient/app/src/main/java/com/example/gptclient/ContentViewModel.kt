package com.example.gptclient

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

data class GptRequest(
    val prompt: String,
    val max_tokens: Int,
    val model: String
)

data class GptResponse(
    val choices: List<Choice>
)

data class Choice(
    val text: String
)

interface GptApi {
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer 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"
    )
    @POST("/v1/completions")
    fun getCompletion(
        @Body requestBody: GptRequest
    ): Call<GptResponse>
}


class ContentViewModel : ViewModel() {

    var answer by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openai.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(GptApi::class.java)

    fun sendRequest(question: String) {
        val request = GptRequest(
            prompt = question,
            max_tokens = 100,
            model = "text-davinci-003"
        )

        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            val call = api.getCompletion(request)
            val response = call.execute()
            isLoading = false

            if (response.isSuccessful) {
                val choice = response.body()?.choices?.get(0)
                viewModelScope.launch(Dispatchers.Main) {
                    choice?.text?.let {
                        answer = it
                    }
                }
            } else {
                viewModelScope.launch(Dispatchers.Main) {
                    answer = "Error: ${response.code()} - ${response.message()}"
                }
            }
        }
    }
}
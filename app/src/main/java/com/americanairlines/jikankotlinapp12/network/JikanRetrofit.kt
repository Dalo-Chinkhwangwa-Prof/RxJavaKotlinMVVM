package com.americanairlines.jikankotlinapp12.network

import com.americanairlines.jikankotlinapp12.model.JikanResponse
import com.americanairlines.jikankotlinapp12.util.Constants.Companion.BASE_URL
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
//Singleton creation in Kotlin (object keyword)
class JikanRetrofit {

    private var jikanAPI: JikanAPI

    init {
        jikanAPI = createJikanAPI(createRetrofitInstance())
    }

    private fun createRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createJikanAPI(retrofit: Retrofit): JikanAPI =
        retrofit.create(JikanAPI::class.java)
    //Java: retrofit.create(JikanAPI.class)

    fun getSearchQuery(searchQuery: String): Observable<JikanResponse> = jikanAPI.searchJikanAnime(searchQuery)

}






























package com.americanairlines.jikankotlinapp12.network

import com.americanairlines.jikankotlinapp12.model.JikanResponse
import com.americanairlines.jikankotlinapp12.util.Constants.Companion.JIKAN_PATH
import com.americanairlines.jikankotlinapp12.util.Constants.Companion.SEARCH_QUERY
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface JikanAPI {
    //https://api.jikan.moe/v3/search/anime?q=naruto

    @GET(JIKAN_PATH)
    fun searchJikanAnime(@Query(SEARCH_QUERY) searchQuery: String): Observable<JikanResponse>
    //Java
    //public Observable<JikanResponse> searchJikanAnime(@Query(SEARCH_QUERY) String searchQuery)

            //RxJava has 5 types of observables
            //1. Observable<T>: 1 to many emissions of data
            //2. Flowable<T>: just like the observable - but it has back pressure handling
            //3. Completable<T>: only interested in completion - eg. like a download
            //4. Maybe<T>: Maybe is similar to Single only difference being that it allows for no emissions as well.
            //5. Single<T>: Only allows for one emission of data - 1 result expected
}
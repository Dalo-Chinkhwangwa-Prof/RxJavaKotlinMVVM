package com.americanairlines.jikankotlinapp12.util

class Constants {
    //https://api.jikan.moe/v3/search/anime?q=naruto
    companion object{
        //const is compile time immutability and val is runtime immutability
        //API
        const val BASE_URL = "https://api.jikan.moe/"
        const val JIKAN_PATH = "/v3/search/anime"
        const val SEARCH_QUERY = "q"

        //Broadcast
        const val FRAGMENT_BROADCAST_ACTION = "com.from.jikan.fragment"

    }
}
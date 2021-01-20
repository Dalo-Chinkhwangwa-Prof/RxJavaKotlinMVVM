package com.americanairlines.jikankotlinapp12.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.americanairlines.jikankotlinapp12.model.JikanResult
import com.americanairlines.jikankotlinapp12.network.JikanRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class JikanViewModel: ViewModel() {

    private val compositeDisposable: CompositeDisposable = /*new*/ CompositeDisposable()

    val jikanLiveData: MutableLiveData<List<JikanResult>> = MutableLiveData()

    private val jikanRetrofit: JikanRetrofit = JikanRetrofit()

    fun getSearchResults(searchQuery: String) {
        //rxJava implementation
        compositeDisposable.add(
            jikanRetrofit.getSearchQuery(searchQuery)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map {
                    it.jikanResults
                }
                .subscribe ({
                    if(it.isNotEmpty())
                        jikanLiveData.postValue(it)
                    compositeDisposable.clear()
                }, {
                    Log.d("TAG_X", "${it.localizedMessage}")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        JikanViewModelFactory.viewModel = null
        Log.d("TAG_X", "onClear called..")

    }
}







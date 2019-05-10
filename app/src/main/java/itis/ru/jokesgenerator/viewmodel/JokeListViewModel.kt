package itis.ru.jokesgenerator.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList
import io.reactivex.rxkotlin.subscribeBy
import itis.ru.jokesgenerator.api.Response
import itis.ru.jokesgenerator.data.Joke
import itis.ru.jokesgenerator.data.JokeRepository
import itis.ru.jokesgenerator.paging.ItemDataSourceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import javax.inject.Inject

class JokeListViewModel @Inject constructor(private val jokeRepository: JokeRepository) :
    ViewModel() {
    private val mNavigateToDetails = MutableLiveData<Event<String>>()

    val navigateToDetails: LiveData<Event<String>>
        get() = mNavigateToDetails

    private val mJokesLiveData: MutableLiveData<Response<List<Joke>>> = MutableLiveData()

    var itemPagedList: LiveData<PagedList<Joke>>? = null
    var liveDataSource: LiveData<PageKeyedDataSource<Int, Joke>>? = null

    init {
        init()
    }

    private fun init() {
        val itemDataSourceFactory =
            ItemDataSourceFactory(jokeRepository)
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }

    /*fun getJokeList(): LiveData<Response<List<Joke>>> {
        jokeRepository.getJokeList()
            .subscribeBy(onSuccess = {
                mJokesLiveData.value = Response.success(it)
            }, onError = {
                mJokesLiveData.value = Response.error(it)
            })
        return mJokesLiveData
    }*/

    fun onJokeClicked(joke: Joke) {
        mNavigateToDetails.value = Event(joke.text ?: "")
    }

    fun refreshPulled() = itemPagedList
}

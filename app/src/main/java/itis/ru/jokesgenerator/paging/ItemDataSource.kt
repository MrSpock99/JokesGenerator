package itis.ru.jokesgenerator.paging

import android.arch.paging.PageKeyedDataSource
import android.util.Log


import io.reactivex.rxkotlin.subscribeBy
import itis.ru.jokesgenerator.data.Joke
import itis.ru.jokesgenerator.data.JokeRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val FIRST_PAGE = 1

class ItemDataSource(
    private val repository: JokeRepository
) : PageKeyedDataSource<Int, Joke>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Joke>
    ) {
        /*repository.getJokeList().subscribeBy(onSuccess = {
            callback.onResult(it, null, FIRST_PAGE + 1)
        })*/
        GlobalScope.launch {
            callback.onResult(repository.getJokeList(), null, FIRST_PAGE + 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Joke>) {
        /*repository.getJokeList().subscribeBy(onSuccess = {
            Log.d("MYLOG", "after ${params.key.toString()}")
            val key = (if (it.size == params.key) params.key + 1 else null)
            callback.onResult(it, key)
        })*/
        GlobalScope.launch {
            val list = repository.getJokeList()
            val key = (if (list.size == params.key) params.key + 1 else null)
            callback.onResult(list, key)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Joke>) {
      /*  repository.getJokeList().subscribeBy(onSuccess = {
            Log.d("MYLOG", "before ${params.key.toString()}")
            val key = (if (params.key > 1) params.key - 1 else null)
            callback.onResult(it, key)
        })*/
        GlobalScope.launch {
            val list = repository.getJokeList()
            val key = (if (params.key > 1) params.key - 1 else null)
            callback.onResult(list, key)
        }
    }
}

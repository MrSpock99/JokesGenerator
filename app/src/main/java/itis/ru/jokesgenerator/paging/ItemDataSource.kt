package itis.ru.jokesgenerator.paging

import android.arch.paging.PageKeyedDataSource


import io.reactivex.rxkotlin.subscribeBy
import itis.ru.jokesgenerator.data.Joke
import itis.ru.jokesgenerator.data.JokeRepository

private const val FIRST_PAGE = 1

class ItemDataSource constructor(
    private val repository: JokeRepository
) : PageKeyedDataSource<Int, Joke>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Joke>
    ) {
        repository.getJokeList().subscribeBy(onSuccess = {
            callback.onResult(it, null, FIRST_PAGE + 1)
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Joke>) {
        repository.getJokeList().subscribeBy(onSuccess = {
            val key = (if (it.size == params.key) params.key + 1 else null)
            callback.onResult(it, key)
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Joke>) {
        repository.getJokeList().subscribeBy(onSuccess = {
            val key = (if (params.key > 1) params.key - 1 else null)
            callback.onResult(it, key)
        })
    }
}

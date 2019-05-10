package itis.ru.jokesgenerator.data

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import itis.ru.jokesgenerator.api.JokeGeneratorApi
import itis.ru.jokesgenerator.database.JokeDataDao
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JokeRepository constructor(
    private val jokesDao: JokeDataDao,
    private val api: JokeGeneratorApi
) {
    suspend fun getJokeList(): List<Joke> = withContext(Dispatchers.IO) {
        val list: MutableList<Joke> = mutableListOf()
        for (i in 0 until 10){
            list.add(api.getRandomJokeAsync().await())
        }
        return@withContext list
        /*return Observable.fromIterable(ITERABLE_ARRAY)
            .flatMap {
                api.getRandomJokeAsync()
            }
            .concatMap {
                Observable.just(it)
            }
            .toList()
            .map {
                jokesDao.nukeTable()
                it
            }
            .map {
                jokesDao.insert(it)
                it
            }.doOnError {
                Log.d("MYLOG", it.message)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())*/
    }

    companion object {
        private val ITERABLE_ARRAY = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    }
}

package itis.ru.jokesgenerator.data

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import itis.ru.jokesgenerator.api.JokeGeneratorApi
import itis.ru.jokesgenerator.database.JokeDataDao

class JokeRepository constructor(
    private val jokesDao: JokeDataDao,
    private val api: JokeGeneratorApi
) {
    fun getJokeList(): Single<List<Joke>> {
        return Observable.fromIterable(ITERABLE_ARRAY)
            .flatMap {
                api.getRandomJoke()
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
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getJoke(): Observable<Joke> = api.getRandomJoke()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    companion object {
        private val ITERABLE_ARRAY = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    }
}

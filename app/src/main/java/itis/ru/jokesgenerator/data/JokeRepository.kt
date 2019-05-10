package itis.ru.jokesgenerator.data

import itis.ru.jokesgenerator.api.JokeGeneratorApi
import itis.ru.jokesgenerator.database.JokeDataDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class JokeRepository constructor(
    private val jokesDao: JokeDataDao,
    private val api: JokeGeneratorApi
) {
    suspend fun getJokeList(): List<Joke> = withContext(Dispatchers.IO) {
        val list: MutableList<Joke> = mutableListOf()
        try {
            for (i in 0 until 10) {
                list.add(api.getRandomJokeAsync().await())
            }
        } catch (ex: UnknownHostException) {
            ex.printStackTrace()
            return@withContext jokesDao.getAllAsync().await()
        }
        jokesDao.insert(list)
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

package itis.ru.jokesgenerator.api

import io.reactivex.Observable
import itis.ru.jokesgenerator.data.Joke
import retrofit2.http.GET
import retrofit2.http.Headers

interface JokeGeneratorApi {
    @GET("/")
    @Headers("Accept: application/json")
    fun getRandomJoke(): Observable<Joke>
}

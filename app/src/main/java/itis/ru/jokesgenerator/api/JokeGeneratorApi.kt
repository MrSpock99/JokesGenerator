package itis.ru.jokesgenerator.api

import itis.ru.jokesgenerator.data.Joke
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers

interface JokeGeneratorApi {
    @GET("/")
    @Headers("Accept: application/json")
    fun getRandomJokeAsync(): Deferred<Joke>
}

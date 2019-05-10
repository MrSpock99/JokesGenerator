package itis.ru.jokesgenerator.database

import androidx.room.*
import io.reactivex.Single
import itis.ru.jokesgenerator.data.Joke
import kotlinx.coroutines.Deferred

@Dao
interface JokeDataDao {
    @Query("SELECT * FROM joke")
    fun getAllAsync(): Deferred<List<Joke>>

    @Query("SELECT * FROM joke WHERE id = :id")
    fun getByIdAsync(id: Int): Deferred<Joke>

    @Query("DELETE FROM joke")
    fun nukeTable()

    @Insert
    fun insert(insult: Joke)

    @Insert
    fun insert(insults: List<Joke>)

    @Update
    fun update(insults: Joke)

    @Delete
    fun delete(insult: Joke)
}

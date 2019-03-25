package itis.ru.jokesgenerator.database

import android.arch.persistence.room.*
import io.reactivex.Single
import itis.ru.jokesgenerator.data.Joke

@Dao
interface JokeDataDao {
    @Query("SELECT * FROM joke")
    fun getAll(): Single<List<Joke>>

    @Query("SELECT * FROM joke WHERE id = :id")
    fun getById(id: Int): Single<Joke>

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

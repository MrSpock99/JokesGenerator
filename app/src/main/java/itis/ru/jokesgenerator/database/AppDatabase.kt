package itis.ru.jokesgenerator.database

import androidx.room.Database
import androidx.room.RoomDatabase
import itis.ru.jokesgenerator.data.Joke

const val DB_NAME: String = "JOKE.db"

@Database(entities = [Joke::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jokeDataDao(): JokeDataDao
}

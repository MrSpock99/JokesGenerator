package itis.ru.jokesgenerator.di.module

import androidx.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import itis.ru.jokesgenerator.api.JokeGeneratorApi
import itis.ru.jokesgenerator.data.JokeRepository
import itis.ru.jokesgenerator.database.AppDatabase
import itis.ru.jokesgenerator.database.DB_NAME
import itis.ru.jokesgenerator.database.JokeDataDao
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideDb(): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, DB_NAME
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideJokeDao(appDatabase: AppDatabase): JokeDataDao = appDatabase.jokeDataDao()

    @Provides
    @Singleton
    fun provideRepository(jokeDataDao: JokeDataDao, api: JokeGeneratorApi): JokeRepository =
        JokeRepository(jokeDataDao, api)
}

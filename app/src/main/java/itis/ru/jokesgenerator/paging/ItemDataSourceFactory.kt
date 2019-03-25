package itis.ru.jokesgenerator.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import itis.ru.jokesgenerator.data.Joke
import itis.ru.jokesgenerator.data.JokeRepository

class ItemDataSourceFactory(private val repository: JokeRepository) :
    DataSource.Factory<Int, Joke>() {
    private val itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Joke>>()

    override fun create(): DataSource<Int, Joke> {
        val itemDataSource = ItemDataSource(repository)
        itemLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }

    fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, Joke>> {
        return itemLiveDataSource
    }
}

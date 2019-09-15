package itis.ru.jokesgenerator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import itis.ru.jokesgenerator.data.JokeRepository
import javax.inject.Inject

class GetJokeViewModel @Inject constructor(
    private val jokeRepository: JokeRepository
) : ViewModel() {

    val jokeLiveData: MutableLiveData<String> = MutableLiveData()

    fun getJokeClick() {
        jokeRepository.getJoke()
            .subscribe({
                jokeLiveData.value = it.text
            }, {
                jokeLiveData.value = "You're joke"
            })
    }
}
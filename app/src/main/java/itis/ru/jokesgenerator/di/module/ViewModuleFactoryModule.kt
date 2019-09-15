package itis.ru.jokesgenerator.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import itis.ru.jokesgenerator.viewmodel.ViewModelFactory

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}

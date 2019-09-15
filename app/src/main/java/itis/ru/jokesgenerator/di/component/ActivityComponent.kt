package itis.ru.jokesgenerator.di.component

import dagger.Component
import itis.ru.jokesgenerator.activity.GetInsultActivity
import itis.ru.jokesgenerator.di.module.DatabaseModule
import itis.ru.jokesgenerator.di.module.NetModule
import itis.ru.jokesgenerator.di.module.ViewModelFactoryModule
import itis.ru.jokesgenerator.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelFactoryModule::class, ViewModelModule::class, NetModule::class, DatabaseModule::class])
interface ActivityComponent {
    fun inject(getInsultActivity: GetInsultActivity)
}

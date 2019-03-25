package itis.ru.jokesgenerator.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import itis.ru.jokesgenerator.R
import kotlinx.android.synthetic.main.activity_joke.*

class JokeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke)
        init()
    }

    private fun init() {
        tv_joke.text = intent.extras[JokeListActivity.EXTRA_JOKE].toString()
    }
}

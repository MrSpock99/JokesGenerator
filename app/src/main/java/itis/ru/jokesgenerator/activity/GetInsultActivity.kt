package itis.ru.jokesgenerator.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import itis.ru.jokesgenerator.Ad
import itis.ru.jokesgenerator.R
import itis.ru.jokesgenerator.di.component.DaggerActivityComponent
import itis.ru.jokesgenerator.di.module.DatabaseModule
import itis.ru.jokesgenerator.di.module.NetModule
import itis.ru.jokesgenerator.viewmodel.GetJokeViewModel
import itis.ru.jokesgenerator.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_get_joke.*
import javax.inject.Inject

class GetInsultActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModeFactory: ViewModelFactory
    private lateinit var viewModel: GetJokeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependency()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_joke)

        viewModel =
            ViewModelProviders.of(this, this.viewModeFactory)
                .get(GetJokeViewModel::class.java)

        btn_get_joke.setOnClickListener {
            pb_dots.visibility = View.VISIBLE
            tv_joke.text = ""
            viewModel.getJokeClick()
        }

        tv_joke.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.primaryClip = ClipData.newPlainText("", tv_joke.text.toString())
            Toast.makeText(this, "Text is copied", Toast.LENGTH_SHORT).show()
        }

        viewModel.jokeLiveData.observe(this, Observer {
            pb_dots.visibility = View.GONE
            tv_joke.text = it
        })

        Ad.showBannerAd(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_privacy_policy -> {
                startActivity(Intent(this, PrivacyPolicyActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .databaseModule(DatabaseModule(this))
            .netModule(NetModule())
            .build()
        activityComponent.inject(this)
    }
}

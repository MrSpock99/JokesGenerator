package itis.ru.jokesgenerator.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import itis.ru.jokesgenerator.R
import itis.ru.jokesgenerator.data.Joke
import itis.ru.jokesgenerator.data.JokesAdapter
import itis.ru.jokesgenerator.di.component.DaggerActivityComponent
import itis.ru.jokesgenerator.di.module.DatabaseModule
import itis.ru.jokesgenerator.di.module.NetModule
import itis.ru.jokesgenerator.viewmodel.JokeListViewModel
import itis.ru.jokesgenerator.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_joke_list.*
import kotlinx.android.synthetic.main.item_joke.*
import javax.inject.Inject

class JokeListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModeFactory: ViewModelFactory

    private lateinit var mViewModel: JokeListViewModel
    private var adapter: JokesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependency()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke_list)
        init()
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .databaseModule(DatabaseModule(this))
            .netModule(NetModule())
            .build()
        activityComponent.inject(this)
    }

    private fun init() {
        rv_jokes.apply {
            layoutManager = LinearLayoutManager(this@JokeListActivity)
            setHasFixedSize(true)
        }
        initAdapter()
        mViewModel =
            ViewModelProviders.of(this, this.viewModeFactory).get(JokeListViewModel::class.java)
        swipeContainer.setOnRefreshListener {
            mViewModel.refreshPulled()
            showProgress()
        }
        observeJokeList()
        observeJokeClick()
        observePullRefresh()
    }

    private fun observeJokeList() =
        mViewModel.itemPagedList?.observe(this, Observer {
            when {
                it != null -> {
                    adapter?.submitList(it)
                }
            }
        })
    /* mViewModel.getJokeList().observe(this, Observer {
         when {
             it?.data != null -> {
                 adapter?.submitList(it.data)
             }
             it?.error != null -> {
                 Snackbar.make(
                     layout_joke_list, it.error.message
                         ?: getString(R.string.sb_error), Snackbar.LENGTH_SHORT
                 )
             }
         }
     })*/

    private fun observeJokeClick() =
        mViewModel.navigateToDetails.observe(this, Observer { joke ->
            joke?.getContentIfNotHandled().let {
                val intent = Intent(this, JokeActivity::class.java)
                intent.putExtra(EXTRA_JOKE, joke?.peekContent())
                val transitionName = getString(R.string.transition)
                val sharedView = tv_joke
                val transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    sharedView,
                    transitionName
                )
                startActivity(intent, transitionActivityOptions.toBundle())
            }
        })

    private fun observePullRefresh() =
        mViewModel.itemPagedList?.observe(this, Observer {
            when {
                it != null -> {
                    hideProgress()
                    adapter?.submitList(it)
                }
            }
        })
       /* mViewModel.refreshPulled().observe(this, Observer {
            when {
                it?.data != null -> {
                    adapter?.submitList(it.data)
                    hideProgress()
                }
                it?.error != null -> {
                    Snackbar.make(
                        layout_joke_list, it.error.message
                            ?: getString(R.string.sb_error), Snackbar.LENGTH_SHORT
                    )
                }
            }
        })*/


    private fun showProgress() {
        swipeContainer.isRefreshing = true
    }

    private fun hideProgress() {
        swipeContainer.isRefreshing = false
    }

    private fun initAdapter() {
        adapter =
            JokesAdapter { joke -> mViewModel.onJokeClicked(joke) }
        rv_jokes.adapter = adapter
    }

    companion object {
        const val EXTRA_JOKE: String = "EXTRA_JOKE"
    }
}

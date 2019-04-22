package itis.ru.jokesgenerator.data

import android.arch.paging.PagedList
import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import itis.ru.jokesgenerator.R
import kotlinx.android.synthetic.main.activity_joke.view.*

class JokesAdapter(
    private val insultLambda: (Joke) -> Unit
) : PagedListAdapter<Joke, JokesAdapter.InsultViewHolder>(InsultDiffCallback()) {

    var list: List<Joke>? = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): InsultViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        return InsultViewHolder(
            inflater.inflate(
                R.layout.item_joke,
                p0,
                false
            )
        )
    }

    override fun onBindViewHolder(p0: InsultViewHolder, p1: Int) {
        p0.tvJoke.text = getItem(p1)?.text
//        p0.icon.setImageResource(getItem(p1).);
        p0.itemView.setOnClickListener {
            getItem(p1)?.let { it1 -> insultLambda.invoke(it1) }
        }
    }

    override fun submitList(list: PagedList<Joke>?) {
        this.list = list
        super.submitList(list)
    }

    class InsultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvJoke: TextView = itemView.tv_joke
    }

    class InsultDiffCallback : DiffUtil.ItemCallback<Joke>() {
        override fun areItemsTheSame(oldItem: Joke, newItem: Joke): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Joke, newItem: Joke): Boolean = oldItem == newItem
    }
}

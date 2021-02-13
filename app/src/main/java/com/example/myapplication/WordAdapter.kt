package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_group_list.view.*

class WordAdapter(
    private val context: Context,
    private val listener: OnItemClickListener,
    private val wordList: OrderedRealmCollection<Word>?,
    private val autoUpdate: Boolean
) :

    RealmRecyclerViewAdapter<Word, WordAdapter.WordViewHolder>(wordList, autoUpdate) {
    override fun getItemCount(): Int = wordList?.size ?: 0
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word: Word = wordList?.get(position) ?: return
        holder.titleTextView.text = word.title

        holder.container.setOnClickListener {
            listener.onItemClick(word)
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int): WordViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_word_list, viewGroup, false)
        return WordViewHolder(v)
    }

    class WordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.titleTextView
        val container: LinearLayout = view.container
    }

    interface OnItemClickListener {
        fun onItemClick(item: Word)
    }
}
package com.example.myapplication

import android.content.Context
import android.widget.AdapterView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class GroupAdapter(
    private val cntext: Context,
    private val listener: AdapterView.OnItemClickListener,
    private val groupList: OrderedRealmCollection<Group>?,
    private val autoUpdate:Boolean
):
        RealmRecyclerViewAdapter<Group,GroupAdapter.GroupViewHolder>(groupList,autoUpdate){


    override fun getItemCount(): Int =groupList?.size?:0
    override fun onBindViewHolder(holder:GroupViewHolder, position: Int) {
        val group: Group = groupList?.get(position) ?: return
        holder.titileTextView.text = group.title

        holder.container.setOnClickListener {
            listener.onItemClick(group)
        }
    }
        }
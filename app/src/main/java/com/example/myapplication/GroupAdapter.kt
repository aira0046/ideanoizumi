package com.example.myapplication

import android.content.Context
import android.widget.AdapterView
import io.realm.OrderedRealmCollection

class GroupAdapter(
    private val cntext: Context,
    private val listener: AdapterView.OnItemClickListener,
    private val groupList: OrderedRealmCollection<Group>?,
    private val autoUpdate:Boolean
):
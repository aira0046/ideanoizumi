package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import io.realm.Realm
import io.realm.RealmRecyclerViewAdapter
import io.realm.RealmResults
import io.realm.Sort
import java.util.*
import kotlinx.android.synthetic.main.activity_group_list.*

class GroupListActivity : AppCompatActivity() {

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_list)

        val groupList: RealmResults<Group> = readAll()
        val listener = object :
            GroupAdapter.OnItemClickListener {
            override fun onItemClick(item: Group) {
                TODO("Not yet implemented")
            }
        }
        val adapter = GroupAdapter(
            this,
            listener,
            groupList,
            true
        )

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter

    }


    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun readAll(): RealmResults<Group> {
        return realm.where(Group::class.java).findAll().sort("createdAt", Sort.ASCENDING)
    }

    fun create(title: String) {
        realm.executeTransaction {
            val group: Group = it.createObject(
                Group::class.java, UUID.randomUUID().toString ()
            )
            group.title = title
        }
    }
}



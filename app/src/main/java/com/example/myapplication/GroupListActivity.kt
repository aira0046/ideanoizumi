package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.RealmRecyclerViewAdapter
import io.realm.RealmResults
import io.realm.Sort
import java.util.*
import kotlinx.android.synthetic.main.activity_group_list.*
import kotlinx.android.synthetic.main.item_group_list.*
import java.net.URI.create

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
                val intent = Intent(applicationContext, WordListActivity::class.java)
                intent.putExtra("GROUP_ID", item.id)
                startActivity(intent)

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

        floatingActionButton.setOnClickListener {
            val editText = EditText(this)
            editText.hint = "グループ名"
            AlertDialog.Builder(this)
                .setTitle("グループの作成")
                .setView(editText)
                .setPositiveButton("作成") { _, _ ->
                    val title: String = editText.text.toString()
                    if (title.isBlank()) {
                        Snackbar.make(container, "グループ名を入れてね！", Snackbar.LENGTH_SHORT).show()
                    } else {
                        create(title)
                    }}
                        .setNeutralButton("キャンセル", null)
                        .show()
                }
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
                    Group::class.java, UUID.randomUUID().toString()
                )
                group.title = title
            }
        }
    }



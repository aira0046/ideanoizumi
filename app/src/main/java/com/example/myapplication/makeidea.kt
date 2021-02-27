package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_makeidea.*
import java.util.*
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class makeidea : AppCompatActivity() {
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_makeidea)

        val firstGroupId = intent.getStringExtra("FIRST_GROUP_ID")?:""
        val secondGroupId = intent.getStringExtra("SECOND_GROUP_ID")?:""

        val firstGroupWordList = readAll(firstGroupId)
        val secondGroupWordList = readAll(secondGroupId)

        var firstRandomNum = 0
        var secondRandomNum = 0

        ideaTextView1.text = firstGroupWordList[firstRandomNum]?.title
        ideaTextView2.text = secondGroupWordList[secondRandomNum]?.title

        nextButton.setOnClickListener{
            firstRandomNum = Random.nextInt(firstGroupWordList.size)
            secondRandomNum = Random.nextInt(secondGroupWordList.size)
            ideaTextView1.text = firstGroupWordList[firstRandomNum]?.title
            ideaTextView2.text = secondGroupWordList[secondRandomNum]?.title
        }
    }

    fun readAll(groupId: String):RealmResults<Word>{
        return realm.where(Word::class.java).equalTo("groupId",groupId).findAll()
            .sort("createdAt", Sort.ASCENDING)
    }

}
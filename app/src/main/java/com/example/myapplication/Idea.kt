package com.example.myapplication

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Idea(
    @PrimaryKey
    open var id:String = UUID.randomUUID().toString(),
    open var title:String ="",
    open var content:String ="",
    open var firstWord:String ="",
    open var secondWord:String ="",
    open var createdAt: Date = Date(System.currentTimeMillis())
): RealmObject()
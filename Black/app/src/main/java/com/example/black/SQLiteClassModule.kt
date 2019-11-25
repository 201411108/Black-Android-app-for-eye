package com.example.black

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

public class SQLiteClassModule{
    var id: Int = 0
    var userName: String? = null
    constructor(id: Int, userName: String) {
        this.id = id
        this.userName = userName
    }
    constructor(userName: String) {
        this.userName = userName
    }
}

//ANKO Library : https://github.com/Kotlin/anko/wiki/Anko-SQLite

//인터넷에서 퍼온 예제
class MindOrksDBOpenHelper(context: Context,
                           factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME,
        factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME
                + " TEXT" + ")")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
    fun addName(name: SQLiteClassModule) {
        val values = ContentValues()
        values.put(COLUMN_NAME, name.userName)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
    fun getAllName(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "mindorksName.db"
        val TABLE_NAME = "name"
        val COLUMN_ID = "_id"
        val COLUMN_NAME = "username"
    }
}
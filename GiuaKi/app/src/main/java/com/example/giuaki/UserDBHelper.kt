package com.example.giuaki

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class UserDBHelper(context: Context, factory: CursorFactory?): SQLiteOpenHelper(context, DB_NAME, factory, DB_VERSION) {
    companion object{
        private const val DB_NAME = "userdb"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "user"
        private const val ID_COL = "user_id"
        private const val EMAIl_COL = "user_email"
        private const val NAME_COL = "user_name"
        private const val PASS_COL = "user_pass"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_COL + " TEXT," + EMAIl_COL + " TEXT," + PASS_COL + " TEXT)")
        p0!!.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(p0!!)
    }

    fun addNewUser(userName: String?, userEmail: String?, userPass: String?){
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(NAME_COL, userName)
        values.put(EMAIl_COL, userEmail)
        values.put(PASS_COL, userPass)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun readCourse(): ArrayList<User>? {
        val db = this.readableDatabase

        val cursorCourses: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        val courseModalArrayList: ArrayList<User> = ArrayList()

        if(cursorCourses.moveToFirst()){
            do{
                courseModalArrayList.add(
                    User(
                        userName = cursorCourses.getString(1),
                        userEmail = cursorCourses.getString(2),
                        userPass = cursorCourses.getString(3)
                    )
                )
            }while (cursorCourses.moveToNext())
        }

        cursorCourses.close()
        return  courseModalArrayList
    }

    fun updateCourse(
        pastUserName: String?,
        userName: String?,
        userEmail: String?,
        userPass: String?){
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(NAME_COL, userName)
        values.put(EMAIl_COL, userEmail)
        values.put(PASS_COL, userPass)

        db.update(TABLE_NAME, values, "user_name=?", arrayOf(pastUserName))
        db.close()
    }

//    fun dangNhap(
//        userName: String?,
//        userPass: String?
//    ){
//        val db = this.readableDatabase
//
//        val cursorCourses: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", arrayOf(userName, userPass))
//    }

    fun deleteCourse(userName: String?){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "user_name=?", arrayOf(userName))
        db.close()
    }

}
//package com.angelaud.presentpoofficial.databasehandler
//
//import android.content.ContentValues
//import android.content.Context
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//import android.util.Log
//import com.angelaud.presentpoofficial.model.Teacher
//
//class TeacherHandler(context: Context) : SQLiteOpenHelper(context, TeacherHandler.DB_NAME, null, TeacherHandler.DB_VERSION) {
//
//    companion object {
//        private val DB_VERSION = 1
//        private val DB_NAME = "TeacherDB"
//        private val TABLE_NAME = "Teacher"
//        private val ID = "Id"
//        private val TEACHER_NAME = "teacherName"
//        private val SUBJECT = "subject"
//
//        }
//    override fun onCreate(db: SQLiteDatabase) {
//        val CREATE_TABLE =
//                "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY, $TEACHER_NAME TEXT, $SUBJECT TEXT);"
//                db.execSQL(CREATE_TABLE)
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        val DROP_TABLE = "DROP TABLE IF EXIST $TABLE_NAME"
//        db.execSQL(DROP_TABLE)
//        onCreate(db)
//
//    }
//    fun addTeacher(teacher: Teacher): Boolean{
//        val db = this.writableDatabase
//        val values = ContentValues()
//
//        values.put(TEACHER_NAME, teacher.teacherName)
//        values.put(SUBJECT, teacher.subject)
//
//        val _success = db.insert(TABLE_NAME, null, values)
//        db.close()
//        Log.v("INSERTEDID", "$_success")
//        return (Integer.parseInt("$_success") != -2)
//    }
//    fun getTeacher(_id: Int): Teacher {
//        val teacher = Teacher()
//        val db = writableDatabase
//        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $ID = $_id"
//        val cursor = db.rawQuery(selectQuery, null)
//        teacher.teacherName = cursor.getString(cursor.getColumnIndex(TEACHER_NAME))
//        teacher.subject = cursor.getString(cursor.getColumnIndex(SUBJECT))
//        cursor.close()
//        return teacher
//
//    }
//    fun allTeacher(): List<Teacher>{
//        val teacherList = ArrayList<Teacher>()
//        val db = writableDatabase
//        val selectQuery = "SELECT * FROM $TABLE_NAME"
//        val cursor = db.rawQuery(selectQuery, null)
//        if (cursor != null) {
//            if (cursor.moveToFirst()){
//                do {
//                    val teacher = Teacher()
//                    teacher.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
//                    teacher.teacherName = cursor.getString(cursor.getColumnIndex(TEACHER_NAME))
//                    teacher.subject = cursor.getString(cursor.getColumnIndex(SUBJECT))
//                    teacherList.add(teacher)
//                }while (cursor.moveToNext())
//            }
//        }
//        cursor.close()
//        return teacherList
//    }
//    fun updateTeacher(teacher: Teacher): Boolean{
//        val db = this.writableDatabase
//        val values = ContentValues()
//        values.put(TEACHER_NAME, teacher.teacherName)
//        values.put(SUBJECT, teacher.subject)
//        val _success = db.update(TABLE_NAME, values, ID + "=?", arrayOf(teacher.id.toString())).toLong()
//        db.close()
//        return Integer.parseInt("$_success") != -2
//    }
//    fun deleteTeacher(_id: Int): Boolean{
//        val db = this.writableDatabase
//        val _success = db.delete(TABLE_NAME, ID + "=?", arrayOf(_id.toString())).toLong()
//        db.close()
//        return Integer.parseInt("$_success") != -2
//
//    }
//
//    fun deleteAllTeacher(): Boolean{
//        val db = this.writableDatabase
//        val _success = db.delete(TABLE_NAME, null, null).toLong()
//        db.close()
//        return Integer.parseInt("$_success") != -2
//    }
//
//
//
//
//
//}
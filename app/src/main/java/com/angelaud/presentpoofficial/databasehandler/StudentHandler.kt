package com.angelaud.presentpoofficial.databasehandler

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.angelaud.presentpoofficial.model.Student

class StudentHandler(context: Context) : SQLiteOpenHelper(context, StudentHandler.DB_NAME, null, StudentHandler.DB_VERSION) {

    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "StudentDB"
        private val TABLE_NAME = "Student"
        private val ID = "Id"
        private val STUDENT_NAME = "studentName"
        private val STUDENT_NUMBER = "studentNumber"
        private val ADDRESS = "address"
        private val AGE = "age"
        private val GRADE_SECTION = "gradeSection"
        private val MOTHER_NAME = "motherName"
        private val FATHER_NAME = "fatherName"
        private val MOTHER_CONTACT = "motherContact"
        private val FATHER_CONTACT = "fatherContact"
        private val ADVISER_NAME = "adviserName"
        private val TEACHER_NAME = "teacherName"
        private val SUBJECT = "subject"
//TODO:   private val STUDENT_IMAGE = "studentImage"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE =
                "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY, $STUDENT_NAME TEXT, $STUDENT_NUMBER TEXT, $ADDRESS TEXT, $AGE TEXT, $GRADE_SECTION TEXT, $MOTHER_NAME TEXT, $FATHER_NAME TEXT, $MOTHER_CONTACT TEXT, $FATHER_CONTACT TEXT, $ADVISER_NAME TEXT, $TEACHER_NAME TEXT, $SUBJECT TEXT);"
        // TODO: $STUDENT_IMAGE TEXT
      db.execSQL(CREATE_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun addStudent(student: Student): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(STUDENT_NAME, student.studentName)
        values.put(STUDENT_NUMBER, student.studentNumber)
        values.put(ADDRESS, student.address)
        values.put(AGE, student.age)
        values.put(GRADE_SECTION, student.gradeSection)
        values.put(MOTHER_NAME, student.motherName)
        values.put(FATHER_NAME, student.fatherName)
        values.put(MOTHER_CONTACT, student.motherContact)
        values.put(FATHER_CONTACT, student.fatherContact)
        values.put(ADVISER_NAME, student.adviserName)
        values.put(TEACHER_NAME, student.teacherName)
        values.put(SUBJECT, student.subject)
//TODO:   values.put(STUDENT_IMAGE, student.studentImage)
        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        Log.v("INSERTEDID", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }
    fun getStudent(_id: Int): Student{
        val student = Student()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $ID = $_id"
        val cursor = db.rawQuery(selectQuery, null)

        cursor?.moveToFirst()
        student.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
        student.studentName = cursor.getString(cursor.getColumnIndex(STUDENT_NAME))
        student.studentNumber = cursor.getString(cursor.getColumnIndex(STUDENT_NUMBER))
        student.address = cursor.getString(cursor.getColumnIndex(ADDRESS))
        student.age = cursor.getString(cursor.getColumnIndex(AGE))
        student.gradeSection = cursor.getString(cursor.getColumnIndex(GRADE_SECTION))
        student.motherName = cursor.getString(cursor.getColumnIndex(MOTHER_NAME))
        student.fatherName = cursor.getString(cursor.getColumnIndex(FATHER_NAME))
        student.motherContact = cursor.getString(cursor.getColumnIndex(MOTHER_CONTACT))
        student.fatherContact = cursor.getString(cursor.getColumnIndex(FATHER_CONTACT))
        student.adviserName = cursor.getString(cursor.getColumnIndex(ADVISER_NAME))
        student.teacherName = cursor.getString(cursor.getColumnIndex(TEACHER_NAME))
        student.subject = cursor.getString(cursor.getColumnIndex(SUBJECT))
//TODO:  student.studentImage = cursor.getString(cursor.getColumnIndex(STUDENT_IMAGE))
        cursor.close()
        return student
    }

    fun allStudent(): List<Student>{
        val studentList = ArrayList<Student>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()){
                do {
                    val student = Student()
                    student.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                    student.studentName = cursor.getString(cursor.getColumnIndex(STUDENT_NAME))
                    student.studentNumber = cursor.getString(cursor.getColumnIndex(STUDENT_NUMBER))
                    student.address = cursor.getString(cursor.getColumnIndex(ADDRESS))
                    student.age = cursor.getString(cursor.getColumnIndex(AGE))
                    student.gradeSection = cursor.getString(cursor.getColumnIndex(GRADE_SECTION))
                    student.motherName = cursor.getString(cursor.getColumnIndex(MOTHER_NAME))
                    student.fatherName = cursor.getString(cursor.getColumnIndex(FATHER_NAME))
                    student.motherContact = cursor.getString(cursor.getColumnIndex(MOTHER_CONTACT))
                    student.fatherContact = cursor.getString(cursor.getColumnIndex(FATHER_CONTACT))
                    student.adviserName = cursor.getString(cursor.getColumnIndex(ADVISER_NAME))
                    student.teacherName = cursor.getString(cursor.getColumnIndex(TEACHER_NAME))
                    student.subject = cursor.getString(cursor.getColumnIndex(SUBJECT))
            //        student.studentImage = cursor.getString(cursor.getColumnIndex(STUDENT_IMAGE))
                    studentList.add(student)
                }while (cursor.moveToNext())
            }
        }
        cursor.close()
        return studentList
    }
    fun updateStudent(student: Student): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(STUDENT_NAME, student.studentName)
        values.put(STUDENT_NUMBER, student.studentNumber)
        values.put(ADDRESS, student.address)
        values.put(AGE, student.age)
        values.put(GRADE_SECTION, student.gradeSection)
        values.put(MOTHER_NAME, student.motherName)
        values.put(FATHER_NAME, student.fatherName)
        values.put(MOTHER_CONTACT, student.motherContact)
        values.put(FATHER_CONTACT, student.fatherContact)
        values.put(ADVISER_NAME, student.adviserName)
        values.put(TEACHER_NAME, student.teacherName)
        values.put(SUBJECT, student.subject)
//TODO: values.put(STUDENT_IMAGE, student.studentImage)

        val _success = db.update(TABLE_NAME, values, ID + "=?", arrayOf(student.id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    fun deleteStudent(_id: Int): Boolean{
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, ID + "=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1

    }
    fun deleteAllStudent(): Boolean{
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, null, null).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }




}
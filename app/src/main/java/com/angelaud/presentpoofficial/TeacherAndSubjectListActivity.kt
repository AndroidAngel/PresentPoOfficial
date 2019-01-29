package com.angelaud.presentpoofficial

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.angelaud.presentpoofficial.databasehandler.StudentHandler
import com.angelaud.presentpoofficial.model.Student
import kotlinx.android.synthetic.main.add_teacher_subject.*

class TeacherAndSubjectListActivity : AppCompatActivity() {

       var dbHandler: StudentHandler? = null
       var isEditAddMode = false

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.add_teacher_subject)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            initDB()
            initOperations()
}
    private fun initDB() {
        dbHandler = StudentHandler(this)
        ts_btn_delete_id.visibility = View.VISIBLE
        if (intent != null && intent.getStringExtra("Mode") == "E") {
            isEditAddMode = true
            val student: Student = dbHandler!!.getStudent(intent.getIntExtra("Id", 0))
            add_teacher_id.setText(student.teacherName)
            add_subject_id.setText(student.subject)
        }
    }
    private fun initOperations() {
        ts_btn_save_id.setOnClickListener {
            var success: Boolean = false
            if (!isEditAddMode) {
                val student: Student = Student()
                student.teacherName = add_teacher_id.text.toString()
                student.subject = add_subject_id.text.toString()
                success = dbHandler?.updateStudent(student) as Boolean
            }else{
                val student: Student = Student()
                student.id = intent.getIntExtra("Id", 0)
                student.teacherName = add_teacher_id.text.toString()
                student.subject = add_subject_id.text.toString()
                success = dbHandler?.updateStudent(student) as Boolean
            }
            if (success)
                finish()
            Snackbar.make(ts_btn_save_id,"Successfully Saved", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
        }
        ts_btn_delete_id.setOnClickListener{
            val dialog = AlertDialog.Builder(this).setTitle("Info").setMessage("Click 'YES' to delete.")
                    .setPositiveButton("YES") {dialog, i ->
                        val success = dbHandler?.deleteStudent(intent.getIntExtra("Id", 0)) as Boolean
                        if (success)
                            finish()
                        dialog.dismiss()
                    }
                    .setNegativeButton("NO") {dialog, i ->
                        dialog.dismiss()
                    }
            dialog.show()
        }
    }


}

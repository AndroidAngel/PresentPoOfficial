package com.angelaud.presentpoofficial

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.angelaud.presentpoofficial.databasehandler.StudentHandler
import com.angelaud.presentpoofficial.model.Student
import kotlinx.android.synthetic.main.activity_add_student.*


class AddStudentActivity : AppCompatActivity() {

    var dbHandler: StudentHandler? = null
    var isEditAddMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initDB()
        initOperations()


        add_btn_subject_advisory_id.setOnClickListener{
            val i = Intent(this, TeacherAndSubjectListActivity::class.java)
            startActivity(i)

        }
    }

    private fun initDB() {
        dbHandler = StudentHandler(this)
        add_btn_delete_id.visibility = View.INVISIBLE
        if (intent != null && intent.getStringExtra("Mode") == "E") {
            isEditAddMode = true
            val student: Student = dbHandler!!.getStudent(intent.getIntExtra("Id", 0))
            add_student_name_id.setText(student.studentName)
            add_student_no_id.setText(student.studentNumber)
            add_student_address_id.setText(student.address)
//TODO     add_age_spinner_id.onItemSelectedListener(student.age)
            add_grade_section_id.setText(student.gradeSection)
            add_mother_name_id.setText(student.motherName)
            add_mother_contact_id.setText(student.motherContact)
            add_father_name_id.setText(student.fatherName)
            add_father_contact_id.setText(student.fatherContact)
            add_adviser_name_id.setText(student.adviserName)
//            add_teacher_id.setText(student.teacherName)
//            add_subject_id.setText(student.subject)
//TODO     add_profile_image_id.setImageResource(student.studentImage)
            add_btn_delete_id.visibility = View.VISIBLE
        }
    }

    private fun initOperations() {
        add_btn_save_id.setOnClickListener {
            var success: Boolean = false
            if (!isEditAddMode) {
                val student: Student = Student()
                student.studentName = add_student_name_id.text.toString()
                student.studentNumber = add_student_no_id.text.toString()
                student.address = add_student_address_id.text.toString()
//          TODO        student.age = add_age_spinner_id.text.toString
                student.gradeSection = add_grade_section_id.text.toString()
                student.motherName = add_mother_name_id.text.toString()
                student.fatherName = add_father_name_id.text.toString()
                student.motherContact = add_mother_contact_id.text.toString()
                student.fatherContact = add_father_contact_id.text.toString()
                student.adviserName = add_adviser_name_id.text.toString()
//                student.teacherName = add_teacher_id.text.toString()
//                student.subject = add_subject_id.text.toString()
//     TODO     student.studentImage = add_profile_image_id.imageAlpha.toString()
                success = dbHandler?.addStudent(student) as Boolean
            }else{
                val student: Student = Student()
                student.id = intent.getIntExtra("Id", 0)
                student.studentName = add_student_name_id.text.toString()
                student.studentNumber = add_student_no_id.text.toString()
                student.address = add_student_address_id.text.toString()
//          TODO        student.age = add_age_spinner_id.text.toString
                student.gradeSection = add_grade_section_id.text.toString()
                student.motherName = add_mother_name_id.text.toString()
                student.fatherName = add_father_name_id.text.toString()
                student.motherContact = add_mother_contact_id.text.toString()
                student.fatherContact = add_father_contact_id.text.toString()
                student.adviserName = add_adviser_name_id.text.toString()
//                student.teacherName = add_teacher_id.text.toString()
//                student.subject = add_subject_id.text.toString()
//     TODO     student.studentImage = add_profile_image_id.imageAlpha.toString()
                success = dbHandler?.updateStudent(student) as Boolean
            }
            if (success)
                finish()
            Snackbar.make(add_btn_save_id,"Student Successfully Saved", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            Toast.makeText(this, "Student save", Toast.LENGTH_SHORT).show()

        }
        add_btn_delete_id.setOnClickListener{
            val dialog = AlertDialog.Builder(this).setTitle("Info").setMessage("Click 'YES' to delete the student.")
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home){
            finish()
        return true
    }
        return super.onOptionsItemSelected(item)
    }
}


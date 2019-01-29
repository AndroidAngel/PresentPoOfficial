package com.angelaud.presentpoofficial

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.angelaud.presentpoofficial.databasehandler.StudentHandler
import com.angelaud.presentpoofficial.model.Student
import kotlinx.android.synthetic.main.activity_student_profile.*

class StudentProfileActivity : AppCompatActivity() {

    var dbHandler: StudentHandler? = null
    var fabTeacher: FloatingActionButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initDB()
        initOperations()

        fabTeacher?.setOnClickListener { view ->
            val intent = Intent(this, TeacherAndSubjectListActivity::class.java)
            startActivity(intent)

        }
    }

    fun initDB() {
        dbHandler = StudentHandler(this)
        val student: Student = dbHandler!!.getStudent(intent.getIntExtra("Id", 0))
        student_name_id.text = student.studentName
        student_no_id.text = student.studentNumber
        address_id.text = student.address
        //TODO AGE
        grade_section_id.text = student.gradeSection
        mother_name_id.text = student.motherName
        mother_contact_id.text = student.motherContact
        father_contact_id.text = student.fatherContact
        father_name.text = student.fatherName
        adviser_id.text = student.adviserName

    }

    fun initOperations(){
        fabTeacher?.setOnClickListener { view ->
            val i = Intent(applicationContext, TeacherAndSubjectListActivity::class.java)
            i.putExtra("Mode", "A")
            startActivity(i)
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.profile_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_delete){
            val dialog = AlertDialog.Builder(this).setTitle("Info").setMessage("Click 'YES' to delete")
                    .setPositiveButton("YES") { dialog, i ->
                        val success = dbHandler?.deleteStudent(intent.getIntExtra("Id",0)) as Boolean
                        if (success)
                            finish()
                        dialog.dismiss()
                    }
                    .setNegativeButton("NO") { dialog, i ->
                        dialog.dismiss()
                    }
            dialog.show()
            return true
        }

        if (id == R.id.action_edit){
            dbHandler = StudentHandler(this)
            val student: Student = dbHandler!!.getStudent(intent.getIntExtra("Id", 0))
            val i = Intent(this, AddStudentActivity::class.java)
            i.putExtra("Mode", "E")
            i.putExtra("Id", student.id)
           startActivity(i)

        }
        return super.onOptionsItemSelected(item)


    }

    override fun onResume() {
        super.onResume()
        initDB()
    }
}


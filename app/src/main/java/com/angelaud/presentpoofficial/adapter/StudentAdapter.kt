package com.angelaud.presentpoofficial.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.angelaud.presentpoofficial.R
import com.angelaud.presentpoofficial.StudentProfileActivity
import com.angelaud.presentpoofficial.model.Student

class StudentAdapter(studentList: List<Student>,
                     internal var context: Context) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    internal var studentList: List<Student> = ArrayList()
    init {
        this.studentList = studentList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.studentName.text = student.studentName
        holder.studentNumber.text = student.studentNumber
        holder.gradeSection.text = student.gradeSection
//        holder.address.text = student.address
//        holder.age.text = student.age
//        holder.motherName.text = student.motherName
//        holder.fatherName.text = student.fatherName
//        holder.motherContact.text = student.motherContact
//        holder.fatherContact.text = student.fatherContact
//        holder.adviserName.text = student.adviserName
//        holder.teacherName.text = student.teacherName
//        holder.subject.text = student.subject
       //TODO holder.studendImage.text = student.studentImage
        holder.itemView.setOnClickListener{
            val i = Intent(context, StudentProfileActivity::class.java)
            i.putExtra("Mode", "E")
            i.putExtra("Id", student.id)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
    inner class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var studentName: TextView = view.findViewById(R.id.student_name_list_id)
        var studentNumber: TextView = view.findViewById(R.id.student_number_list_id)
        var gradeSection: TextView = view.findViewById(R.id.student_grade_section_list_id)

        var list_item: LinearLayout = view.findViewById(R.id.list_item) as LinearLayout
    }
}
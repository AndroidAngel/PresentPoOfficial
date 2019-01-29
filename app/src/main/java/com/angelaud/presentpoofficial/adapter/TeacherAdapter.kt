//package com.angelaud.presentpoofficial.adapter
//
//import android.content.Context
//import android.content.Intent
//import android.os.Build
//import android.support.annotation.RequiresApi
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.LinearLayout
//import android.widget.TextView
//import com.angelaud.presentpoofficial.R
//import com.angelaud.presentpoofficial.TeacherAndSubjectListActivity
//import com.angelaud.presentpoofficial.model.Student
//
//
//class TeacherAdapter (studentList: List<Student>,
//                      internal var context: Context) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>(){
//
//    internal var studentList: List<Student> = ArrayList()
//    init {
//        this.studentList = studentList
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.teachers_list_item, parent, false)
//       return TeacherViewHolder(view)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
//    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
//        val student = studentList[position]
//        holder.teacherName.text = student.teacherName
//        holder.subject.text = student.subject
//        holder.itemView.setOnClickListener{
//            val i = Intent(context, TeacherAndSubjectListActivity::class.java)
//            i.putExtra("Mode", "E")
//            i.putExtra("Id", student.id)
//            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            context.startActivity(i)
//        }
//    }
//    override fun getItemCount(): Int {
//        return studentList.size
//    }
//    inner class TeacherViewHolder(view: View) : RecyclerView.ViewHolder(view){
//        var teacherName: TextView = view.findViewById(R.id.teacher_list_item_id)
//        var subject: TextView = view.findViewById(R.id.subject_list_item_id)
//
//        var teacher_list_item: LinearLayout = view.findViewById(R.id.teacher_list_item) as LinearLayout
//    }
//
//}
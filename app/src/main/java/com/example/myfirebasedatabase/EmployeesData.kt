package com.example.myfirebasedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EmployeesData : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var employeeList:MutableList<Employees>
    lateinit var listview: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees_data)
        employeeList = mutableListOf()
        listview = findViewById(R.id.listview1)
        ref = FirebaseDatabase.getInstance().getReference("Employees")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()){
                    employeeList.clear()
                    for (e in snapshot.children){
                        val employee = e.getValue(Employees::class.java)
                        employeeList.add(employee!!)
                    }
                    val adapter = EmployeeAdapter(this@EmployeesData,R.layout.employees,employeeList)
                    listview.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}
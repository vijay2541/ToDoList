package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoadapter: Todoadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoadapter = Todoadapter(mutableListOf())

        rvTodoItems.adapter = todoadapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        add_to_list.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if (todoTitle.isNotEmpty()){
                val todo = Todolist(todoTitle)
                todoadapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }
        delete.setOnClickListener {
            todoadapter.delete()
        }
    }
}
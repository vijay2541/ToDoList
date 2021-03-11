package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class Todoadapter (
    private val todos: MutableList<Todolist>
    ) : RecyclerView.Adapter<Todoadapter.TodoViewHolder>()
{
    class TodoViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_todo,
            parent,
            false
        )
        )

    }

    fun addTodo(todo: Todolist) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }
    fun delete(){
        todos.removeAll { todo ->
            todo.ischecked
        }
        notifyDataSetChanged()
    }

    private fun notifyDataSetChanged(position: Any) {

    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
        tvTodoTitle.text = curTodo.title
            checkBox.isChecked = curTodo.ischecked
            toggleStrikeThrough(tvTodoTitle, curTodo.ischecked)
            checkBox.setOnCheckedChangeListener{ _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                curTodo.ischecked = !curTodo.ischecked
            }

        }

    }

    override fun getItemCount(): Int {
        return todos.size

    }
}
package np.com.bimalkafle.todoapp

import java.time.Instant
import java.util.Date

object TodoManager {

    private val todoList = mutableListOf(
        Todo(1, "Nicolas", Date.from(Instant.now()))
    )


    fun getAllTodo() : List<Todo>{
        return todoList
    }

    fun addTodo(title : String){
        todoList.add(Todo(System.currentTimeMillis().toInt(),title, Date.from(Instant.now())))
    }

    fun deleteTodo(id : Int){
        todoList.removeIf{
            it.id==id
        }
    }

    fun updateTodo(id : Int, newTitle : String){
        val todo = todoList.find { it.id == id }
        if(todo != null){
            todo.title = newTitle
        }
    }

}
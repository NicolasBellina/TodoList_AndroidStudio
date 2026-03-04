package np.com.bimalkafle.todoapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import np.com.bimalkafle.todoapp.data.model.Todo
import np.com.bimalkafle.todoapp.data.manager.TodoManager

class TodoViewModel : ViewModel() {

    private var _todoList = MutableLiveData<List<Todo>>()
    val todoList: LiveData<List<Todo>> = _todoList

    init {
        getAllTodo()
    }

    fun getAllTodo() {
        _todoList.value = TodoManager.getAllTodo().reversed()
    }

    fun addTodo(title: String) {
        TodoManager.addTodo(title)
        getAllTodo()
    }

    fun deleteTodo(id: Int) {
        TodoManager.deleteTodo(id)
        getAllTodo()
    }

    fun updateTodo(id: Int, newTitle: String) {
        TodoManager.updateTodo(id, newTitle)
        getAllTodo()
    }
}

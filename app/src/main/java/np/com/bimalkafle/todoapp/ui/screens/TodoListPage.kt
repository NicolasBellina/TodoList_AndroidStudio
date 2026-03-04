package np.com.bimalkafle.todoapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import np.com.bimalkafle.todoapp.R
import np.com.bimalkafle.todoapp.data.model.Todo
import np.com.bimalkafle.todoapp.ui.viewmodel.TodoViewModel
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun TodoListPage(viewModel: TodoViewModel) {

    val todoList by viewModel.todoList.observeAsState()
    var inputText by remember {
        mutableStateOf("")
    }
    var editingTodoId by remember { mutableStateOf<Int?>(null) }
    var editingText by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = inputText,
                onValueChange = {
                    inputText = it
                }
            )
            Button(onClick = {
                if (inputText.isNotBlank()) {
                    viewModel.addTodo(inputText)
                    inputText = ""
                }
            }) {
                Text(text = "Add")
            }
        }

        // Dialog global de modification
        if (editingTodoId != null) {
            AlertDialog(
                onDismissRequest = {
                    editingTodoId = null
                    editingText = ""
                },
                title = { Text("Modifier la tâche") },
                text = {
                    OutlinedTextField(
                        value = editingText,
                        onValueChange = { editingText = it },
                        label = { Text("Titre de la tâche") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (editingText.isNotBlank() && editingTodoId != null) {
                                viewModel.updateTodo(editingTodoId!!, editingText)
                                editingTodoId = null
                                editingText = ""
                            }
                        }
                    ) {
                        Text("Sauvegarder")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            editingTodoId = null
                            editingText = ""
                        }
                    ) {
                        Text("Annuler")
                    }
                }
            )
        }

        todoList?.let {
            LazyColumn {
                items(it, key = { todo -> todo.id }) { item ->
                    TodoItem(
                        item = item,
                        onDelete = {
                            viewModel.deleteTodo(item.id)
                        },
                        onEdit = {
                            editingTodoId = item.id
                            editingText = item.title
                        }
                    )
                }
            }
        } ?: Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "No items yet",
            fontSize = 16.sp
        )


    }

}

@Composable
fun TodoItem(item: Todo, onDelete: () -> Unit, onEdit: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = SimpleDateFormat("HH:mm:aa, dd/mm", Locale.ENGLISH).format(item.createdAt),
                fontSize = 12.sp,
                color = Color.LightGray
            )
            Text(
                text = item.title,
                fontSize = 20.sp,
                color = Color.White
            )
        }
        IconButton(onClick = onEdit) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_edit_24),
                contentDescription = "Edit",
                tint = Color.White
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}

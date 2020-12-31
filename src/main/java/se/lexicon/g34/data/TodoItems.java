package se.lexicon.g34.data;


import se.lexicon.g34.model.Todo;


import java.util.Arrays;

public class TodoItems {
    private static Todo[] todoList = new Todo[0];
    private static TodoSequencer counter = new TodoSequencer();

    //Constructor
    public TodoItems() {
    }

    //methods
    public int size() {
        return todoList.length;
    }

    public Todo[] findAll() {
        return todoList;

    }

    public Todo findByID(int findTodo) {
        if (findTodo >= 0) {
            for (Todo checkTodo : todoList) {
                if (findTodo == checkTodo.getTodoId()) {
                    return checkTodo;
                }
            }
        }
        return new Todo(-1, "Invalid");//With person set to -1 the caller can take care of not existing

    }

    public Todo addTodo(String description) {
        int newId = counter.nextTodoId();
        Todo newTodo = new Todo(newId, description);
        todoList = Arrays.copyOf(todoList, size() + 1);
        todoList[size() - 1] = newTodo;
        return newTodo;
    }

    public void clear() {
        clearTodoList();
    }


    private void clearTodoList() {
        todoList = new Todo[0];
    }
}
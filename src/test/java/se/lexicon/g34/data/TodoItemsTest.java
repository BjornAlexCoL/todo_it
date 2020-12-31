package se.lexicon.g34.data;

import org.junit.Test;
import se.lexicon.g34.model.Todo;


public class TodoItemsTest {


    @Test
    public void testTodoItems() { //test size(), addPerson() and findAll()
        TodoItems todoList = new TodoItems();
        todoList.addTodo("TodoIt");
        todoList.addTodo("Vendor Machine");
        todoList.addTodo("Another thing that must be done");
        todoList.addTodo("Another thing I want to do");
        todoList.addTodo("One more thing to do");
        printTodoList(todoList.findAll());
        System.out.println(todoList.size() + " ToDo's in the list");
        System.out.println("------------------------------------------"); //Testing findById by searching for all numbers if many
        Todo searchTodo;
        for (int i = -1; i < todoList.size() + 1; i++) {//If Persons is removed and new is added this test will not find them.
            System.out.println("Search for Todo with Id=" + i);
            searchTodo = todoList.findByID(i);
            if (searchTodo.getTodoId() == -1) {
                System.out.println("Todo with Id " + i + " can't be found");
                continue;
            }
            printTodo(searchTodo);
        }
        System.out.println("------------------------------------------");//Testing clear()
        System.out.println("Clear the list");
        todoList.clear();
        printTodoList(todoList.findAll());
        System.out.println(todoList.size() + " todo's in the list");
    }

    public void printTodoList(Todo[] list) {
        for (Todo listTodo : list) {
            printTodo(listTodo);
        }
    }

    public void printTodo(Todo todoToPrint) {
        System.out.print(" ID " + todoToPrint.getTodoId() + " " + todoToPrint.getDescription());
        //todoToPrint.getAssignee().printFullName();
        System.out.print(" ");
        todoToPrint.printIsDone();
        System.out.print("\n");

    }

}
package se.lexicon.g34.data;

import org.junit.Before;
import org.junit.Test;
import se.lexicon.g34.model.Person;
import se.lexicon.g34.model.Todo;


public class TodoItemsTest {
    static TodoItems todoList = new TodoItems();//list of todoitems
    static Todo[] findResultList = new Todo[0];//Make new resultlist
    static People volontares = new People(); //list of volontares

    @Before
    public void Init() {
        findResultList = new Todo[0];//Clear the results
        todoList.addTodo("TodoIt");
        todoList.addTodo("Vendor Machine");
        todoList.addTodo("Another thing that must be done");
        todoList.addTodo("Another thing I want to do");
        todoList.addTodo("One more thing to do");
        printTodoList(todoList.findAll());
        System.out.println("------------------------------------------");
        volontares.addPerson("Björn", "Larsson");
        volontares.addPerson("Nisse", "Hult");
        volontares.addPerson("Lazy", "One");

    }

    @Test
    public void testFindById() {
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
    }

    @Test
    public void testFindByDoneStatus() {
        System.out.println("Search the TodoItems after done Todo's");
        findResultList = todoList.findByDoneStatus(true);
        printTodoList(findResultList);
        todoList.setIsDone(todoList.findByID(1)); //Todoitem 1 is done
        todoList.setIsDone(todoList.findByID(3));//Todoitem 3 is done
        System.out.println("Todo 1 and 3 is done check again");
        findResultList = todoList.findByDoneStatus(true);
        printTodoList(findResultList);
        System.out.println("------------------------------------------"); //Testing find by done if true by searching for all numbers if many
        System.out.println("Search the TodoItems after not done Todo's");
        findResultList = todoList.findByDoneStatus(false);
        printTodoList(findResultList);
        System.out.println("Todo 1 and 3 is reset check again");
        todoList.clearIsDone(todoList.findByID(1)); //Todoitem 1 is done
        todoList.clearIsDone(todoList.findByID(3));//Todoitem 3 is done
        findResultList = todoList.findByDoneStatus(false);
        printTodoList(findResultList);
    }

    @Test
    public void testClear() {
        System.out.println("Clear the list");
        todoList.clear();
        printTodoList(todoList.findAll());
    }

    @Test
    public void findUnassignedTodoItems() { //check not assigned to anyone and assign to of different types.
        System.out.println("Find TotoItens not assigned to anyone"); //Nothing shjould be assinged
        findResultList = todoList.findUnassignedTodoItems();
        printTodoList(findResultList);
        System.out.println("Assigning 1 and 3");
        todoList.setTodoAssignee(todoList.findByID(3), volontares.findById(1));
        todoList.setTodoAssignee(todoList.findByID(1), volontares.findById(0));
        printTodoList(findResultList);
        System.out.println("Run another check");
        findResultList = todoList.findUnassignedTodoItems();//1 and 3 should be removed
        printTodoList(findResultList);
    }

    @Test
    public void testfindByAssignee() {//Testing find by Assignee
        assignmentsOfTodo();
        for (Person findPerson : volontares.findAll()) {
            System.out.print("Checking Todos assigned to ");
            findPerson.printFullName();
            System.out.println(" by sending personobject");
            findResultList = todoList.findByAssignee(findPerson); //by Personobject
            printTodoList(findResultList);
        }

        for (Person findPerson : volontares.findAll()) {
            System.out.print("Checking Todos assigned to ");
            findPerson.printFullName();
            System.out.println(" by sending id of person");
            findResultList = todoList.findByAssignee(findPerson.getPersonId());//bu id of person
            printTodoList(findResultList);
        }

    }

    public void assignmentsOfTodo() {//assing todos to persons
        todoList.setTodoAssignee(todoList.findByID(0), volontares.findById(1));
        todoList.setTodoAssignee(todoList.findByID(1), volontares.findById(0));
        todoList.setTodoAssignee(todoList.findByID(2), volontares.findById(0));
        todoList.setTodoAssignee(todoList.findByID(3), volontares.findById(1));
        todoList.setTodoAssignee(todoList.findByID(4), volontares.findById(0));

    }

    public void printTodoList(Todo[] list) {
        for (Todo listTodo : list) {
            printTodo(listTodo);
        }
        System.out.println(list.length + " items in the list");
    }

    public void printTodo(Todo todoToPrint) {
        System.out.print("ID " + todoToPrint.getTodoId() + " " + todoToPrint.getDescription());
        if (todoToPrint.getAssignee() != null) {
            System.out.print(" assigned to ");
            todoToPrint.getAssignee().printFullName();
        } else {
            System.out.print(" not assigned");
        }
        System.out.print(" ");
        todoToPrint.printIsDone();
        System.out.println();
    }

}
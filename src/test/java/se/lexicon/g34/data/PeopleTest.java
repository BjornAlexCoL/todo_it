package se.lexicon.g34.data;

import org.junit.Before;
import org.junit.Test;
import se.lexicon.g34.model.Person;


public class PeopleTest {


    @Test
    public void testPeople() { //test size(), addPerson() and findAll()
        People peopleList = new People();
        peopleList.addPerson("Björn", "Larsson");
        peopleList.addPerson("Sven", "Ohlsson");
        peopleList.addPerson("Ida", "Ohlsson");
        peopleList.addPerson("Harriet", "Larsson");
        peopleList.addPerson("Gösta", "Larsson");
        printPersonList(peopleList.findAll());
        System.out.println(peopleList.size() + " persons in the list");
        System.out.println("------------------------------------------"); //Testing findById by searching for all numbers if many
        Person searchPerson;
        for (int i = -1; i < peopleList.size() + 1; i++) {//If Persons is removed and new is added this test will not find them.
            System.out.println("Search for person with Id=" + i);
            searchPerson = peopleList.findById(i);
            if (searchPerson.getPersonId() == -1) {
                System.out.println("Person with Id " + i + " can't be found");
                continue;
            }
            printPerson(searchPerson);
        }
        System.out.println("------------------------------------------");//Testing clear()
        System.out.println("Clear the list");
        peopleList.clear();
        printPersonList(peopleList.findAll());
        System.out.println(peopleList.size() + " persons in the list");
    }

    public void printPersonList(Person[] list) {
        for (Person listPerson : list) {
            printPerson(listPerson);
        }
    }

    public void printPerson(Person personToPrint) {
        System.out.print(personToPrint.getPersonId() + ". ");
        personToPrint.printFullName();
        System.out.print("\n");
    }

}
package se.lexicon.g34.data;


import se.lexicon.g34.model.Person;
import se.lexicon.g34.data.PersonSequencer;

import java.util.Arrays;

public class People {
    private static Person[] personList = new Person[0];
    private static PersonSequencer counter=new PersonSequencer();
    //Constructor
    public People() {
    }

    //methods
    public int size() {
        return personList.length;
    }

    public Person[] findAll() {
        return personList;

    }

    public Person findById(int findPerson) {
        if (findPerson>=0 ) {
            for (Person checkPerson : personList) {
                if (findPerson == checkPerson.getPersonId()) {
                    return checkPerson;
                }
            }
        }
        return new Person(-1,"Invalid","PersonID");//With person set to -1 the caller can take care of not existing

    }

    public Person addPerson(String firstName,String lastName) {
        int newId=counter.nextPersonId();
        Person newPerson=new Person(newId,firstName,lastName);
        personList=Arrays.copyOf(personList,size()+1);
        personList[size()-1]=newPerson;
        return newPerson;
    }

    public void clear() {
        clearPersonList();
    }


    private void clearPersonList() {
        personList = new Person[0];
    }
}
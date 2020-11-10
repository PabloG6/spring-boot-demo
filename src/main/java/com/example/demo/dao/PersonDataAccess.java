package com.example.demo.dao;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Person;

import org.springframework.stereotype.Repository;

@Repository("FakeDao")
public class PersonDataAccess implements PersonDao {
    private static final List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        // TODO Auto-generated method stub
        DB.add(new Person(id, person.getName()));
        return 0;
    }

    @Override
    public List<Person> allPersons() {
        // TODO Auto-generated method stub
        return DB;
    }

    @Override
    public int deletePerson(UUID id) {
        // TODO Auto-generated method stub
        Optional<Person> maybePerson = getPerson(id);
        if (maybePerson.isEmpty())
            return 0;
        DB.remove(maybePerson.get());
        return 1;
    }

    @Override
    public int updatePerson(UUID id, Person person) {
        return getPerson(id).map(p -> {
            int index = DB.indexOf(person);
            if (index >= 0) {
                DB.set(index, person);
                return 1;

            }

            return 0;
        }).orElse(0);

    }

    @Override
    public Optional<Person> getPerson(UUID id) {
        // TODO Auto-generated method stub
        return DB.stream().filter(person -> person.getUuid().equals(id)).findFirst();
    }

}

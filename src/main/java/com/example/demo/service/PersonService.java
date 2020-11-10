package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("FakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getPersons() {
        return personDao.allPersons();
    }

    public Optional<Person> getPerson(UUID id) {
        return personDao.getPerson(id);
    }

    public int deletePerson(UUID id) {
        return personDao.deletePerson(id);

    }

    public int updatePerson(UUID id, Person newPerson) {
        return personDao.updatePerson(id, newPerson);
    }
}

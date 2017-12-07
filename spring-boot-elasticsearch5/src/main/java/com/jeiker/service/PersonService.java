package com.jeiker.service;

import com.jeiker.model.Person;

import java.util.Map;

public interface PersonService {
    Map<String, Object> savePerson(Person p);

    Map<String, Object> updatePerson(Person p);

    Map<String, Object> delPerson(String id);

    Map<String, Object> findPerson(String id);

    Map<String, Object> queryPerson(Person p);
}

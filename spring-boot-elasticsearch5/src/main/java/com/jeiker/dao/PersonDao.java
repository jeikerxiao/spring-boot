package com.jeiker.dao;

import com.jeiker.model.Person;

public interface PersonDao {

    String save(Person person);

    String update(Person person);

    String deltele(String id);

    Object find(String id);

    Object query(Person person);
}

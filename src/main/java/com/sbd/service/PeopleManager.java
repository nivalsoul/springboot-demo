package com.sbd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sbd.dao.ArticleDao;
import com.sbd.dao.PeopleDao;
import com.sbd.model.Article;
import com.sbd.model.People;

@Service
public class PeopleManager {

    @Autowired
    private PeopleDao peopleDao;

    public void savePeople(People people) {
        peopleDao.save(people);
    }

    public void deletePeople(Integer id) {
    	People people = peopleDao.findOne(id);
        if (people != null) {
            peopleDao.delete(id);
        } else {
            throw new IllegalStateException("no such id: " + id);
        }
    }

    public Iterable<People> getAllPeople() {
        Iterable<People> list = peopleDao.findAll();
        return list;
    }
    
    public Page<People> getPeople(Pageable pageable){
    	Page<People> list = peopleDao.findAll(pageable);
        return list;
    }
    
    public People getPeopleById(Integer id) {
    	People people = peopleDao.findOne(id);
    	return people;
	}

    
    public People findByName(String name){
    	People people = peopleDao.findByName(name);
        return people;
    }
    
    public Page<People> findBySourceArticleType(int age, Pageable pageable){
    	Page<People> list = peopleDao.findByAgeRange(age, pageable);
        return list;
    }
}

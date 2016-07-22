package com.sbd.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sbd.model.People;

@Transactional
public interface PeopleDao extends PagingAndSortingRepository<People, Integer> {

	People findByName(String name);
	
	@Query("select p from People p where p.age > ?1")
	Page<People> findByAgeRange(int age, Pageable pageable);
}

package com.sbd.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sbd.model.People;
import com.sbd.service.PeopleManager;

@Controller
@RequestMapping("/people")
public class PeopleController {
	private static final Logger log = Logger.getLogger(PeopleController.class);
	
	@Autowired
	private PeopleManager peopleManager;
	
	@RequestMapping(value="", method = RequestMethod.GET)  
	@ResponseBody
	public Page<People> getPeople( 
			@RequestParam(value = "page", defaultValue="0") Integer page, 
			@RequestParam(value = "size", defaultValue="10") Integer size){
		return peopleManager.getPeople(new PageRequest(page, size, Direction.DESC, "id"));
	}  
	
	@RequestMapping(value="", method = RequestMethod.POST)  
	@ResponseBody
    public String savePeople(People people){   
		if(people.getId() == null){
			log.info("add people:"+JSON.toJSONString(people));
			peopleManager.savePeople(people);
		}else{
			log.info("update people:"+JSON.toJSONString(people));
			People oldData = peopleManager.getPeopleById(people.getId());
			oldData.setName(people.getName());
			oldData.setAge(people.getAge());
			peopleManager.savePeople(oldData);
		}
        
        return "保存成功！";
    }  
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)  
	@ResponseBody
	public People getPeopleById(@PathVariable("id") Integer id){  
		log.info("get people info by id:"+id);  
		return peopleManager.getPeopleById(id);
	}  
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)  
	@ResponseBody
	public Object deletePeopleById(@PathVariable("id") Integer id){  
		log.info("delete people by id:"+id);  
		peopleManager.deletePeople(id);
		return "ok";
	} 
	
}

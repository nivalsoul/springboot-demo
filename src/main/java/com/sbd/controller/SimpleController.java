package com.sbd.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbd.config.ESConfig;

@RestController  
@RequestMapping("/test") 
public class SimpleController {
	
	@Autowired
	private ESConfig esConfig;
	
	@RequestMapping(value ="/file", method = RequestMethod.GET)
    public Object test(HttpServletRequest request){
		String path = request.getServletContext().getRealPath("/");
		File file =  new File(path, "files");
		file.mkdirs();
		try {
			File ff = new File(file, "a.txt");
			ff.createNewFile();
			new PrintStream(ff).println("abcdefg");
			System.out.println("==="+ff.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
        return "http://"+request.getLocalAddr()+":"+request.getLocalPort()
          +"/files/a.txt";
    }
	
	@RequestMapping(value ="/data", method = RequestMethod.GET)
    public Object getData(String name){
		System.out.println("name="+name);
		return esConfig;
    }

}

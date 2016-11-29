package com.niit.crudappfrontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.crudappbackend.dao.PersonDAO;
import com.niit.crudappbackend.model.Person;

@Controller
public class HomeController {
	@Autowired
	PersonDAO personDAO;
	
	/*@RequestMapping("/")
	public ModelAndView indexPage()
	{
		List<Person> listPersons=personDAO.listPersons();
		ModelAndView model=new ModelAndView("index");
		model.addObject("listPersons", listPersons);
		
		
		return model;
	}*/

	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String listPersons(Model model)
	{
		model.addAttribute("person",new Person());
		
		model.addAttribute("listPersons",personDAO.listPersons());
		return "index";
	}
	
	
	
	
	@RequestMapping(value= "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person person)
	{
		if(person.getId()==0)
		{
			personDAO.addPerson(person);
		}
		else
		{
			personDAO.updatePerson(person);
		}
		
		return "redirect:/";
	}
	
	
	@RequestMapping("/remove/{id}")
	public String removePerson(@PathVariable("id") int id)
	{
		personDAO.removePerson(id);
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public String editPerson(@PathVariable("id") int id, Model model)
	{
		model.addAttribute("person", personDAO.getPersonById(id));
        model.addAttribute("listPersons", personDAO.listPersons());
        return "index";
	}
	
}

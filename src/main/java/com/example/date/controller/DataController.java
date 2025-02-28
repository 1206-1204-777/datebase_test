package com.example.date.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.date.entity.DataTestEntity;
import com.example.date.repository.TestDataRepository;

@Controller
public class DataController {
	private TestDataRepository repository;
	public DataController(TestDataRepository repository) {
		this.repository = repository;
	}
	@GetMapping("/")
	public String run() {
		return "index";
	}
	@GetMapping("/hello")
	public String msg(Model model) {
		model.addAttribute("text","Hello Wellcom!");
		return "hello";
		
	}
	@PostMapping("/hello")
	public String input(@RequestParam String input,Model model){
		DataTestEntity entity = new DataTestEntity();
		model.addAttribute("name",input);
		entity.setContent(input);
		repository.saveAndFlush(entity);
		
		/*デバッグ*/
		System.out.println(entity.getContent());
		System.out.println(repository.findAll());
		return "redirect:/hello";
		
	}
	
	

}

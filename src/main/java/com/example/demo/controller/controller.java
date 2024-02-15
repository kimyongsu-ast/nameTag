package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.UserData;

@Controller
public class controller {
	@GetMapping("/index")
	public String main(Model model) {
		model.addAttribute("userData", new UserData());
		
		return "/index";
	}
	
	@PostMapping("result")
	public String result (Model model, UserData userData) throws IOException {
		Resource resource = new ClassPathResource("static/html.txt");
		Path filePath = Path.of(resource.getURI());
		String fileContent = Files.readString(filePath);
		fileContent = fileContent.replace("{{koName}}", userData.getKoName());
		fileContent = fileContent.replace("{{enName}}", userData.getEnName());
		fileContent = fileContent.replace("{{department}}", userData.getDepartment());
		fileContent = fileContent.replace("{{part}}", userData.getPart());
		fileContent = fileContent.replace("{{koPosition}}", userData.getKoPosition());
		fileContent = fileContent.replace("{{enPosition}}", userData.getEnPosition());
		fileContent = fileContent.replace("{{floor}}", userData.getFloor());
		fileContent = fileContent.replace("{{phone}}", userData.getPhone());
		fileContent = fileContent.replace("{{email}}", userData.getEmail());
		
		model.addAttribute("userData", userData);
		
		model.addAttribute("fileContent", fileContent);
		System.out.println(userData.getKoName());
		return "/result";
	}
}

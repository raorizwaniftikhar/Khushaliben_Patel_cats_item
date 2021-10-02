package ca.sheridancollege.khushi.web.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import ca.sheridancollege.khushi.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ca.sheridancollege.khushi.bean.UserRegistrationDto;
import ca.sheridancollege.khushi.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", userService.findAllRole());
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto,
			HttpServletRequest request, @RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {

		if (userService.findByEmail(registrationDto.getEmail()) != null && userService.findByEmail(registrationDto.getEmail()).getEmail() != null) {
			model.addAttribute("exist", true);
			return "registration";
		}

		String root = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
				+ "resources" + File.separator + "static" + File.separator + "image" + File.separator + "user";
		File dir = new File(root);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String path = dir.getAbsolutePath();
		// Get the file and save it somewhere
		byte[] bytes = multipartFile.getBytes();
		Path upload = Paths.get(path + File.separator + multipartFile.getOriginalFilename());
		Files.write(upload, bytes);
		registrationDto.setPicture(multipartFile.getOriginalFilename());
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}

package com.example.date.controller;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.date.entity.UserDataEntity;
import com.example.date.service.ChatDataService;
import com.example.date.service.UserService;

@Controller
public class ChatController {
	private UserService nameService;
	private ChatDataService chatService;

	public ChatController(UserService nameService, ChatDataService chatService) {
		this.nameService = nameService;
		this.chatService = chatService;

	}

	@GetMapping("/")
	public String run() {
		return "index";
	}

	@GetMapping("/hello")
	public String msg(Model model) {
		return "hello";

	}

	@GetMapping("/login")
	public String login(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("text", auth.getName());
		return "login";
	}

	@PostMapping("/register")
	public String inputData(@ModelAttribute UserDataEntity data, RedirectAttributes re) throws IOException {
		if (nameService.isUserExists(data.getUsername())) {
			re.addFlashAttribute("error", "すでに登録済みの名前です");
			return "redirect:/hello";
		}
		re.addFlashAttribute("message", "登録が完了しました！");
		nameService.setData(data);

		return "chat_view";
	}

	@GetMapping("/register")
	public String checkLog(Model model) {
		/**/
		try {
			model.addAttribute("data", nameService.getData());
		} catch (IOException e) {

			e.printStackTrace();
		}
		return "register";
	}

	@PostMapping("/delete")
	public String deleteData(@RequestParam String id) {
		try {
			System.out.println("削除処理呼び出し：" + id);
			nameService.deleteDate(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/register";
	}

	@GetMapping("/chat_view")
	public String chatView() {
		return "chat_view";
	}

}

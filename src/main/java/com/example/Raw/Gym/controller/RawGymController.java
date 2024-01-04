package com.example.Raw.Gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Raw.Gym.model.RawGym;
import com.example.Raw.Gym.service.RawGymService;

@Controller
public class RawGymController {
	@Autowired
	private RawGymService service;
	
	@GetMapping({"/", "viewRawGymList"})
	public String viewAllRawGymItems(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("list", service.getAllRawGymItems());
		model.addAttribute("message", message);
		
		return "ViewRawGymList";
	}

	@GetMapping("/updateRawGymCoins/{id}")
	public String updateRawGymStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if (service.updateRawGymCoins(id)) {
			redirectAttributes.addFlashAttribute("message", "Update Success");
			return "redirect:/viewRawGymList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Update Failure");
		return "redirect:/viewRawGymList";
	}

	@GetMapping("/addRawGymItem")
	public String addRawGymItem(Model model) {
		model.addAttribute("rawgym", new RawGym());
		
		return "AddRawGymItem";
	}

	@PostMapping("/saveRawGymItem")
	public String saveRawGymItem(RawGym rawgym, RedirectAttributes redirectAttributes) {
		if(service.saveOrUpdateRawGymItem(rawgym)) {
			redirectAttributes.addFlashAttribute("message", "Save Success");
			return "redirect:/viewRawGymList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Save Failure");
		return "redirect:/addRawGymItem";
	}
	
	@GetMapping("/editRawGymItem/{id}")
	public String editRawGymItem(@PathVariable Long id, Model model) {
		model.addAttribute("rawgym", service.getRawGymItemById(id));
		
		return "EditRawGymItem";
	}

	@PostMapping("/editSaveRawGymItem")
	public String editSaveRawGymItem(RawGym rawgym, RedirectAttributes redirectAttributes) {
		if(service.saveOrUpdateRawGymItem(rawgym)) {
			redirectAttributes.addFlashAttribute("message", "Edit Success");
			return "redirect:/viewRawGymList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Edit Failure");
		return "redirect:/editRawGymItem/" + rawgym.getId();
	}
	
	@GetMapping("/deleteRawGymItem/{id}")
	public String deleteRawGymItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if (service.deleteRawGymItem(id)) {
			redirectAttributes.addFlashAttribute("message", "Delete Success");
			return "redirect:/viewRawGymList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Delete Failure");
		return "redirect:/viewRawGymList";
	}
}

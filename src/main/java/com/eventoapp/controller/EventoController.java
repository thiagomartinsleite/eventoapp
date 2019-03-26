package com.eventoapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventoapp.models.Evento;
import com.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@RequestMapping(value="/CadastrarEvento", method=RequestMethod.GET)
	public String form() {
		return "eventos/formEvento";
	}
	
	@RequestMapping(value="/CadastrarEvento", method=RequestMethod.POST)
	public String form(@Valid Evento evento, BindingResult bindingResult, RedirectAttributes redirectAttributes){
		evento.setData(evento.getData());
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("mensagem","Evento incompleto!");
			return "redirect:/CadastrarEvento";
		}else {
			eventoRepository.save(evento);
			redirectAttributes.addFlashAttribute("mensagem","Evento Cadastrado com Sucesso!");
			return "redirect:/CadastrarEvento";
		}
		
		
		
	}
}

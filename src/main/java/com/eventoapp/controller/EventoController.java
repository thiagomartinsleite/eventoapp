package com.eventoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String form(Evento evento) {
		eventoRepository.save(evento);
		return "redirect:/CadastrarEvento";
	}
}

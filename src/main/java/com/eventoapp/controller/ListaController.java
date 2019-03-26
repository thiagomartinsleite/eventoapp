package com.eventoapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;
import com.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.repository.EventoRepository;

@Controller
public class ListaController {
	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private ConvidadoRepository convidadoRepository;
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("listaEventos");
		Iterable<Evento> eventos = eventoRepository.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}
	@RequestMapping(value= "/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo){
		Evento evento = eventoRepository.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("eventos/detalhesEvento");
		mv.addObject("evento", evento);
		
		Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
		
		mv.addObject("convidados", convidados);
		return mv;
		
	}
	
	@RequestMapping(value= "/{codigo}", method=RequestMethod.POST)
	public String detalhesEvento(@PathVariable("codigo") long codigo, @Valid Convidado convidado,BindingResult result, RedirectAttributes attributes ){
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem","Verifique os campos!");
			return "redirect:/{codigo}";
			
		}else {
			Evento evento = eventoRepository.findByCodigo(codigo);
			convidado.setEvento(evento);
			convidadoRepository.save(convidado);
			attributes.addFlashAttribute("mensagem","Convidado Adicionado com Sucesso!");
			return "redirect:/{codigo}";	
		}
		
		
		
	}
}

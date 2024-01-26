package pierpaolo.colasante.u5w3d5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pierpaolo.colasante.u5w3d5.entities.Evento;
import pierpaolo.colasante.u5w3d5.services.EventoService;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> getEventi(){return this.eventoService.getEventi();}
}

package pierpaolo.colasante.u5w3d5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.events.Event;
import pierpaolo.colasante.u5w3d5.entities.Evento;
import pierpaolo.colasante.u5w3d5.payloads.EventoDTO;
import pierpaolo.colasante.u5w3d5.services.EventoService;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> getEventi(){return this.eventoService.getEventi();}
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Evento saveEvento(@RequestBody EventoDTO body){
        return eventoService.saveEvento(body);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Evento modificaEvento(@PathVariable long id, @RequestBody Evento body){
        return eventoService.findByIdAndUpdate(id, body);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void eliminaEvento(@PathVariable long id){
         eventoService.findByIdAndDelete(id);
    }
}

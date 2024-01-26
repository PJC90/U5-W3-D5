package pierpaolo.colasante.u5w3d5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pierpaolo.colasante.u5w3d5.entities.Prenotazione;
import pierpaolo.colasante.u5w3d5.entities.Utente;
import pierpaolo.colasante.u5w3d5.services.PrenotazioneService;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Prenotazione> getUtenti(){return this.prenotazioneService.getPrenotazioni();}
}

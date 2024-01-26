package pierpaolo.colasante.u5w3d5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pierpaolo.colasante.u5w3d5.entities.Evento;
import pierpaolo.colasante.u5w3d5.exceptions.BadRequestException;
import pierpaolo.colasante.u5w3d5.exceptions.NotFoundExceptions;
import pierpaolo.colasante.u5w3d5.payloads.EventoDTO;
import pierpaolo.colasante.u5w3d5.repositories.EventoDAO;

import java.util.List;

@Service
public class EventoService {
    @Autowired
    private EventoDAO eventoDAO;

    public List<Evento> getEventi(){return this.eventoDAO.findAll();}
    public Evento findById(long id){return eventoDAO.findById(id).orElseThrow(()->new NotFoundExceptions(id));}
    public Evento findByIdAndUpdate(long id, EventoDTO body){
        Evento found = this.findById(id);
        if (body.titolo() != null) {found.setTitolo(body.titolo());}
        if (body.descrizione() != null) {found.setDescrizione(body.descrizione());}
        if (body.data() != null) {found.setData(body.data());}
        if (body.luogo() != null) {found.setLuogo(body.luogo());}
        if (body.postiDisponibili() != 0) {found.setPostiDisponibili(body.postiDisponibili());}
//        if (body.getAvatar() != null) {found.setAvatar(body.getAvatar());}

        return eventoDAO.save(found);
    }

    public void findByIdAndDelete(long id){
        Evento found = this.findById(id);
        if(!found.getPrenotazioni().isEmpty()){
            throw new BadRequestException("ATTENZIONE impossibile cancellare Evento di id: " + id + " è associato a uno o più prenotazioni.");
        }
        eventoDAO.delete(found);
    }

    public Evento saveEvento(EventoDTO payload){
        Evento newEvento = new Evento();
        newEvento.setTitolo(payload.titolo());
        newEvento.setData(payload.data());
        newEvento.setDescrizione(payload.descrizione());
        newEvento.setLuogo(payload.luogo());
        newEvento.setPostiDisponibili(payload.postiDisponibili());
        return eventoDAO.save(newEvento);
    }

}

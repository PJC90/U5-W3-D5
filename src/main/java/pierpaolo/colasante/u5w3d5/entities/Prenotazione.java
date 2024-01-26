package pierpaolo.colasante.u5w3d5.entities;

import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate dataPrenotazione;
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;
}
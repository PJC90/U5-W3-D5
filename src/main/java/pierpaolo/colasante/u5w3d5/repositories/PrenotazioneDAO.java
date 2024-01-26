package pierpaolo.colasante.u5w3d5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pierpaolo.colasante.u5w3d5.entities.Prenotazione;
@Repository
public interface PrenotazioneDAO extends JpaRepository<Prenotazione, Long> {
}

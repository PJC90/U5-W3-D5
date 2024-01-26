package pierpaolo.colasante.u5w3d5.payloads;

import jakarta.validation.constraints.NotNull;

public record PrenotazioneDTO(
        @NotNull(message = "l'id dell' evento è un campo obbligatorio!")
        int evento_id,
        @NotNull(message = "l'id dell'user è un campo obbligatorio!")
        int utente_id
) {
}

package pierpaolo.colasante.u5w3d5.payloads.errorPayloads;

import java.time.LocalDateTime;

public record ErrorDTO(String message, LocalDateTime time) {
}

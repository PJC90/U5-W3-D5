package pierpaolo.colasante.u5w3d5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pierpaolo.colasante.u5w3d5.entities.Ruolo;
import pierpaolo.colasante.u5w3d5.entities.Utente;
import pierpaolo.colasante.u5w3d5.exceptions.BadRequestException;
import pierpaolo.colasante.u5w3d5.exceptions.UnauthorizedException;
import pierpaolo.colasante.u5w3d5.payloads.UtenteDTO;
import pierpaolo.colasante.u5w3d5.payloads.loginPayload.UtenteLoginDTO;
import pierpaolo.colasante.u5w3d5.repositories.UtenteDAO;
import pierpaolo.colasante.u5w3d5.security.JWTtools;

@Service
public class AuthService {
    @Autowired
    private UtenteService userService;
    @Autowired
    private JWTtools jwTtools;
    @Autowired
    private UtenteDAO userDAO;
    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUser(UtenteLoginDTO body){
        Utente user = userService.findByEmail(body.email());
        if(bcrypt.matches(body.password(), user.getPassword())){
            return jwTtools.createToken(user);

        }else{
            throw new UnauthorizedException("Credenziali non valide!!");
        }

    }
    public Utente save(UtenteDTO body){
        userDAO.findByEmail(body.email()).ifPresent(user -> {throw new BadRequestException("email " + user.getEmail() + " già usata!!!");});
        userDAO.findByUsername(body.username()).ifPresent(user -> {throw new BadRequestException("username " + user.getUsername() + " già in uso!!!");});
        Utente newUser = new Utente();
        newUser.setNome(body.nome());
        newUser.setCognome(body.cognome());
        newUser.setUsername(body.username());
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setRuolo(Ruolo.USER);
        return userDAO.save(newUser);
    }
}

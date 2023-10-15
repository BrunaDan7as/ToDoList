package br.com.danieleleao.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/*
 * Modificador
 * public
 * private
 * protected
*/
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserRepository userRepository;
    /*
     * String
     * integer
     * double
     * float
     * char
     * date
     * void
    */
    @PostMapping("/")
    public ResponseEntity Create(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());
        // Validação de usuário e criação de mensagem de erro HTTP
        if (user != null) {
            System.out.println("Usuário já existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }
        // Criptografar a senha do usuário com bCrypt
        var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
}

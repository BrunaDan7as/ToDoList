package br.com.danieleleao.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Modificador
 * public
 * private
 * protected
*/
@RestController
@RequestMapping("/users")
public class UserController {
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
    public void Create(@RequestBody UserModel userModel) {
        System.out.println(userModel.getUsername());
    }
}

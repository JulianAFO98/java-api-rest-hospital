package api_rest.app.controllers;


import api_rest.app.models.dto.UserDTO;
import api_rest.app.services.implement.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortalController {

    private final UserService userService;

    PortalController(UserService userService) {
        this.userService=userService;
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid  @RequestBody UserDTO userDTO, BindingResult result) {
      if(result.hasErrors()) {
          return ResponseEntity.badRequest().body(result.getAllErrors());
      }

      userService.registerUser(userDTO);
      return ResponseEntity.ok("User created");

    }

}

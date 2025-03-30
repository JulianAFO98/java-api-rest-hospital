package api_rest.app.controllers;

import api_rest.app.models.dto.UserDTO;
import api_rest.app.models.entity.User;
import api_rest.app.models.payload.MessageResponse;
import api_rest.app.services.implement.UserService;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortalController {

    private final UserService userService;

    PortalController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        System.out.println(userDTO);

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(MessageResponse.builder()
                    .message("Datos de usuario incorrectos").object(null).build());
        }
        Optional<User> user = userService.findByEmail(userDTO.getEmail());

        if (user.isPresent()) {
            return ResponseEntity.badRequest().body(MessageResponse.builder()
                    .message("El email ya esta en uso").object(null).build());
        }

        userService.registerUser(userDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User created");
        return ResponseEntity.ok(response);

    }

}

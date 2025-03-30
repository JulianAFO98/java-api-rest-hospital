package api_rest.app.services.interfaces;

import api_rest.app.models.dto.UserDTO;
import api_rest.app.models.entity.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    List<User> listAll();



    User save(UserDTO userDto);

    User registerUser(UserDTO userDTO);

    User findById(UUID id);

    void delete(User user);

    boolean existsById(UUID id);
}

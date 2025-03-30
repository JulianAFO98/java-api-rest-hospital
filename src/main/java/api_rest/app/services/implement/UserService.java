package api_rest.app.services.implement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import api_rest.app.models.dao.UserDAO;
import api_rest.app.models.dto.UserDTO;
import api_rest.app.models.entity.User;
import api_rest.app.services.interfaces.IUserService;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<User> listAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public User registerUser(UserDTO userDto) {

        //Agregar mas operacion Aqui

        User user = User.builder()
                .id(userDto.getId())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .creation_date(userDto.getCreation_date())
                .build();
        return userDao.save(user);
    }


    @Transactional
    @Override
    public User save(UserDTO userDto) {
        User user = User.builder()
                .id(userDto.getId())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .creation_date(userDto.getCreation_date())
                .build();

        return userDao.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(UUID id) {
        return userDao.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void delete(User user) {
       userDao.delete(user);
    }

    @Override
    public boolean existsById(UUID id) {
        return userDao.existsById(id);
    }
}

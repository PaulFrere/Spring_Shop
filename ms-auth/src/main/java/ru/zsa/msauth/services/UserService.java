package ru.zsa.msauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.zsa.msauth.domain.Role;
import ru.zsa.msauth.domain.User;
import ru.zsa.msauth.domain.UserDeliveryAddress;
import ru.zsa.msauth.exeptions.AddressNotFoundException;
import ru.zsa.msauth.repository.RoleRepository;
import ru.zsa.msauth.repository.UserDeliveryAddressRepository;
import ru.zsa.msauth.repository.UserRepository;
import ru.zsa.mscore.model.UserDeliveryAddressDto;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDeliveryAddressRepository userDeliveryAddressRepository;

    public User saveUser(User user) {
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    public void addAddress(UserDeliveryAddressDto addressDto, Integer userId) {
        User user = userRepository.getOne(userId);
        UserDeliveryAddress userDeliveryAddress = new UserDeliveryAddress(addressDto);
        userDeliveryAddress.setUser(user);
        userDeliveryAddressRepository.save(userDeliveryAddress);
    }

    public UserDeliveryAddress getAddress(Long id) {
        return userDeliveryAddressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException("There is no address with id " + id));
    }
}

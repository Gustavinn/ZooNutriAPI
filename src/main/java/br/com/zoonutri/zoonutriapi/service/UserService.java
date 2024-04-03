package br.com.zoonutri.zoonutriapi.service;

import br.com.zoonutri.zoonutriapi.domain.User;
import br.com.zoonutri.zoonutriapi.domain.dto.UserChangeDTO;
import br.com.zoonutri.zoonutriapi.domain.dto.UserDTO;
import br.com.zoonutri.zoonutriapi.domain.dto.UserWithPasswordDTO;
import br.com.zoonutri.zoonutriapi.domain.mapper.UserMapper;
import br.com.zoonutri.zoonutriapi.domain.mapper.UserWithPasswordMapper;
import br.com.zoonutri.zoonutriapi.repository.UserRepository;
import br.com.zoonutri.zoonutriapi.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static br.com.zoonutri.zoonutriapi.security.MyUserDetailsService.MSG_ERROR_AUTHENTICATION_01;
import static br.com.zoonutri.zoonutriapi.service.LogService.USER_ICON;
import static br.com.zoonutri.zoonutriapi.util.GeneralUtil.*;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class UserService {

    public static final String MSG_ERROR_USER_ROLE = "msg.error.user.role";
    public static final String MSG_ERROR_USER_ID = "msg.error.user.id";

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private UserMapper userMapper;
    private UserWithPasswordMapper userWithPasswordMapper;
    private final LogService logService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public List<UserDTO> findUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    public Optional<User> findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    public UserDTO findUserByEmailPassword(final String email, final String password) {
        return userMapper.mapToDto(userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(
                        () -> new IllegalArgumentException(getMessage(MSG_ERROR_AUTHENTICATION_01))));
    }

    public void saveUser(final UserWithPasswordDTO userDTO) {
        veriyEmailAlreadyExists(userDTO);
        final String hashUser = generateHash();
        final User user = userWithPasswordMapper.mapToEntity(userDTO);

        user.setUserRole(userRoleRepository.findById(userDTO.getUserRole().getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        getMessage(MSG_ERROR_USER_ROLE))));

        if (isNull(user.getCreationDate())) {
            user.setCreationDate(new Date());
        }

        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setHash(hashUser);
        user.setActive(Boolean.TRUE);

        userRepository.save(user);
        logService.saveLog(logService.createLogDTO(USER_ICON, "inseriu um usuário às"));
    }

    private void veriyEmailAlreadyExists(UserWithPasswordDTO userDTO) {
        if (userRepository.countByEmail(userDTO.getEmail()) > 0) {
            throw new IllegalArgumentException(getMessage("msg.error.user.email.exists"));
        }
    }

    public void updateUser(final UserWithPasswordDTO userDto) {
        final User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new IllegalArgumentException(getMessage(MSG_ERROR_USER_ID)));

        user.setUserRole(userRoleRepository.findById(userDto.getUserRole().getId())
                .orElseThrow(() -> new IllegalArgumentException(getMessage(MSG_ERROR_USER_ROLE))));

        user.setName(userDto.getName());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail());

        userRepository.save(user);
        logService.saveLog(logService.createLogDTO(USER_ICON, "atualizou um usuário às"));
    }

    private String generateHash() {
        return getMd5(getRandomWord());
    }

    public void deleteUser(final Integer userId) {
        userRepository.delete(userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(getMessage(MSG_ERROR_USER_ID) + userId)));
        logService.saveLog(logService.createLogDTO(USER_ICON, "removeu um usuário às"));
    }

    private User verifyHashAndUser(String userName, String hashUser) throws Exception {
        return userRepository.findByEmailAndHash(userName, hashUser)
                .orElseThrow(() -> new Exception(getMessage("msg.error.authentication.02")));
    }

    public boolean changePassword(UserChangeDTO userChangeDTO) throws Exception {
        User user = verifyHashAndUser(userChangeDTO.getEmail(), userChangeDTO.getHash());
        user.setPassword(bCryptPasswordEncoder.encode(userChangeDTO.getPassword()));
        user.setHash(generateHash());
        userRepository.saveAndFlush(user);
        return Boolean.TRUE;
    }

}

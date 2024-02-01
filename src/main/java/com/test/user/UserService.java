package com.test.user;

import org.springframework.stereotype.Component;

@Component
public class UserService {

        private final UserRepository userRepository;

        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public Iterable<UserDto> getAllUsers() {
            return userRepository.findAll().stream()
                    .map(userEntity -> new UserDto(
                            userEntity.getId(),
                            userEntity.getFirstname(),
                            userEntity.getLastname(),
                            userEntity.getEmail()))
                    .toList();

        }
        public void deleteUser(Integer id) {
            userRepository.deleteById(id);
        }

        public UserDto createUser(UserDto userDto) {
            UserEntity userEntity = userRepository.save(UserEntity.fromDto(userDto));
            return new UserDto(userEntity.getId(), userEntity.getFirstname(), userEntity.getLastname(), userEntity.getEmail());
        }

        public UserDto updateUser(Integer id, UserDto userDto) throws UserNotFoundException {
            UserEntity userEntity = userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
            userEntity.setFirstname(userDto.firstname());
            userEntity.setLastname(userDto.lastname());
            userEntity.setEmail(userDto.email());

            userEntity = userRepository.save(userEntity);
            return new UserDto(userEntity.getId(), userEntity.getFirstname(), userEntity.getLastname(), userEntity.getEmail());
        }

    public UserDto getUser(Integer userId) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return new UserDto(userEntity.getId(), userEntity.getFirstname(), userEntity.getLastname(), userEntity.getEmail());
    }
}

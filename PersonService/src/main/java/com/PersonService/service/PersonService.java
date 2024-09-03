//package com.support_App.service;
//
//
//import com.support_App.enums.Role;
//import com.support_App.exception.EmailNotFoundException;
//import com.support_App.exception.UserNotFoundException;
//import com.support_App.model.Admin;
//import com.support_App.model.Technician;
//import com.support_App.model.User;
//import com.support_App.model.UserU;
//import com.support_App.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//
//public class UserService implements UserDetailsService {
//
//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new EmailNotFoundException("User not found with username: " + email);
//        }
//        return org.springframework.security.core.userdetails.User
//                .withUsername(email)
//                .password(user.getPassword())
//                .roles(user.getRole().name())
//                .build();
//    }
//
////    public User saveUser(User user) {
////        user.setPassword(passwordEncoder.encode(user.getPassword()));
//////        user.setRole(Role.UserU);
////        return userRepository.save(user);
////    }
////
////
////    public User updateUser(User user, Long id) {
////        logger.info("Updating user with ID: {}", id);
////        logger.info("New data: {}", user);
////
////        User userToUpdate = userRepository.findById(id)
////                .orElseThrow(() -> new UserNotFoundException("user not found : " + id));
////
////        userToUpdate.setName(user.getName());
////        userToUpdate.setPassword(user.getEmail());
////
////        User updatedUser = userRepository.save(userToUpdate);
////        logger.info("Updated user: {}", updatedUser);
////
////        return updatedUser;
////    }
////
////
////    public void deleteUser(Long id) {
////        if (!userRepository.existsById(id)) {
////            throw new RuntimeException("User not found!");
////        }
////        userRepository.deleteById(id);
////    }
////
////    public List<User> getAllUsers() {
////        return userRepository.findAll();
////    }
////
////    public User getUserById(Long id) {
////        return userRepository.findById(id)
////                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
////    }
////
//////    public User createAdminUser(String name, String email, String password) {
//////        Admin admin = new Admin();
//////        admin.setName(name);
//////        admin.setEmail(email);
//////        admin.setPassword(passwordEncoder.encode(password));
//////        admin.setRole(Role.Admin);
//////        return userRepository.save(admin);
//////    }
////    public User findByEmail(String email) {
////        return userRepository.findByEmail(email);
//
//
//    public User saveUser(User user) {
//        if (user.getRole() == null) {
//            throw new IllegalArgumentException("User role must be specified.");
//        }
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }
//
//    public User updateUser(User user, Long id) {
//        logger.info("Updating user with ID: {}", id);
//        logger.info("New data: {}", user);
//
//        User userToUpdate = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException("User not found : " + id));
//
//        userToUpdate.setName(user.getName());
//        userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        User updatedUser = userRepository.save(userToUpdate);
//        logger.info("Updated user: {}", updatedUser);
//
//        return updatedUser;
//    }
//
//    public void deleteUser(Long id) {
//        if (!userRepository.existsById(id)) {
//            throw new RuntimeException("User not found!");
//        }
//        userRepository.deleteById(id);
//    }
//
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public User getUserById(Long id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
//    }
//
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//}
package com.PersonService.service;


import com.PersonService.dto.PersonDTO;
import com.PersonService.model.Person;
import com.PersonService.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(email);
        if (person == null) {
            throw new UsernameNotFoundException("User not found with this email: " + email);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(email)
                .password(person.getPassword())
                .roles(person.getRole().name())
                .build();
    }


    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }


    public PersonDTO getPersonInfo(String email) {
        Person person = personRepository.findByEmail(email) ;
        return new PersonDTO(person.getId(), person.getName(), person.getEmail(), person.getRole());
    }
}

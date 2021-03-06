package com.second.template.application.service;

import com.second.template.application.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var userFromDb = userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with this email address."));
        //List<GrantedAuthority> authorities = new java.util.ArrayList<>(Collections.emptyList());

        var authorities = userFromDb.getRoles();
        
        //authorities.add((GrantedAuthority) () -> userFromDb.getRole().name());

        return new User(userFromDb.getUsername(), userFromDb.getPassword(), authorities);
    }
//    public void addUser(UserDto userDto) {
//        if (userRepository.findByUsername(userDto.getUsername()).isEmpty()) {
//
//            var userEntity = new com.spring5.practice.model.User();
//            BeanUtils.copyProperties(userDto, userEntity);
//            userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
//
//            Set<Authority> authorities = new HashSet<Authority>();
//            for (var authorityName : userDto.getAuthorityNames()) {
//                var authority = authorityService.findByRoleName(authorityName);
//                authorities.add(authority);
//            }
//            userEntity.setAuthorities(authorities);
//
//            userRepository.save(userEntity);
//
//        } else {
//            throw new ResourceAlreadyExistsException("Username is unavailable");
//        }
//    }
//
//    public void deleteUser(Long userId) {
//        userRepository.deleteById(userId); 
//    }
  public void update(com.second.template.application.model.User userObj) {
	  userRepository.save(userObj); 
  }
  public Optional<com.second.template.application.model.User> getUserByUserId(Long id) {
	  return userRepository.findById(id); 
  }
    
    
}

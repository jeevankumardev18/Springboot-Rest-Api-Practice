package com.nothing.api.contoller;

import com.nothing.api.entity.User;
import com.nothing.api.entity.UserDaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserResourceController {
    private UserDaoService userDaoService;

   public UserResourceController(UserDaoService userDaoService)
   {
       this.userDaoService=userDaoService;
   }

   @GetMapping("/users")
   public List<User> retrieveAllUsers()
   {
       return userDaoService.findAll();
   }

   @GetMapping("/users/{id}")
   public User retrieveUser(@PathVariable int id)
   {
       return userDaoService.findOne(id);
   }

    @PostMapping("/users")
   public ResponseEntity<User> createUser(@RequestBody User user)
   {
      User savedUser=userDaoService.save(user);
       URI location=ServletUriComponentsBuilder.
               fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

       return ResponseEntity.created(location).build();
   }


}
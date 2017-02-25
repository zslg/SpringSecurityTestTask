package com.example.controller;

import com.example.service.UserService;
import com.example.shared.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    private ResponseEntity<UserDto> create(@NotNull @RequestBody UserDto request){
        UserDto userDto = userService.create(request);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping
    private ResponseEntity<UserDto> update(@NotNull @RequestBody UserDto request){
        UserDto userDto = userService.update(request);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    private ResponseEntity<List<UserDto>> findAll(){
        List<UserDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{userId}")
    private ResponseEntity<UserDto> findById(@NotNull @PathVariable("userId") Long id){
        UserDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/{userId}")
    private ResponseEntity delete(@NotNull @PathVariable("userId") Long id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

}

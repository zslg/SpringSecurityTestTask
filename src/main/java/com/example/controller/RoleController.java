package com.example.controller;

import com.example.service.RoleService;
import com.example.shared.RoleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    private ResponseEntity<RoleDto> create(@NotNull @RequestBody RoleDto request){
        RoleDto roleDto = roleService.create(request);
        return ResponseEntity.ok(roleDto);
    }

    @PutMapping
    private ResponseEntity<RoleDto> update(@NotNull @RequestBody RoleDto request){
        RoleDto roleDto = roleService.update(request);
        return ResponseEntity.ok(roleDto);
    }

    @GetMapping
    private ResponseEntity<List<RoleDto>> findAll(){
        List<RoleDto> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping(value = "/{roleId}")
    private ResponseEntity<RoleDto> findById(@NotNull @PathVariable("roleId") Long id){
        RoleDto role = roleService.findById(id);
        return ResponseEntity.ok(role);
    }

    @DeleteMapping(value = "/{roleId}")
    private ResponseEntity delete(@NotNull @PathVariable("roleId") Long id){
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }

}

package com.example.backend.controller;

import com.example.backend.model.Permission;
import com.example.backend.service.HrServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private HrServiceImplementation hrService;

    @GetMapping
    public List<Permission> getAllPermissions() {
        return hrService.getAllPermissions();
    }
    @GetMapping("/{role}/roles")
    public Set<Permission> getPermissionsByRole(@PathVariable String role){
          return hrService.getPermissionsByRole(role);
    }

    @GetMapping("/{id}")
    public Permission getPermissionById(@PathVariable Long id) {
        return hrService.getPermissionById(id);
    }

    @PostMapping
    public Permission createPermission(@RequestBody Permission permission) {
        return hrService.createPermission(permission);
    }

    @PutMapping("/assign")
    public ResponseEntity<?> assignPermissionsToRole(
            @RequestParam Long roleId,
            @RequestBody List<Long> permissionIds) {

        try {
            hrService.assignPermissionsToRole(roleId, permissionIds);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to assign permissions: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletePermission(@PathVariable Long id) {
        hrService.deletePermission(id);
    }
}

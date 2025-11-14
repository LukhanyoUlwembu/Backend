package com.example.backend.model;
import com.example.backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final RemFormStatusRepository remFormStatusRepository;
    private final OfferLetterStatusRepository offerLetterStatusRepository;

    public DataInitializer(PermissionRepository permissionRepository, RoleRepository roleRepository,RemFormStatusRepository remFormStatusRepository,OfferLetterStatusRepository offerLetterStatusRepository) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.remFormStatusRepository =remFormStatusRepository;
        this.offerLetterStatusRepository = offerLetterStatusRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // 1. Define permissions
        Map<String, String> permissionsMap = Map.ofEntries(
            Map.entry("create_job_requisition", "Create a job requisition"),
            Map.entry("create_job_post", "Create a job post from approved requisitions"),
            Map.entry("create_offer", "Create an offer letter for a candidate"),
            Map.entry("create_rem_form", "Create Rem form for a candidate"),
            Map.entry("view_all_applications", "View all candidate applications"),
            Map.entry("view_longlist", "View longlist of candidates"),
            Map.entry("create_longlist", "Create longlist of candidates"),
            Map.entry("create_shortlist", "Create shortlist of candidates"),
            Map.entry("schedule_interview", "Schedule interviews"),
            Map.entry("manage_users", "Manage system users"),
            Map.entry("manage_permissions", "Manage system permissions"),
            Map.entry("view_reports", "View system reports"),
            Map.entry("view_approvals", "View approvals"),
            Map.entry("approve_approvals", "Approve or reject approvals")
        );

        Map<String,String> statusMap = Map.ofEntries(
        Map.entry("1","MANAGER_APPROVAL_PENDING"),
        Map.entry("2", "HR_APPROVAL_PENDING"),
        Map.entry("3", "CFO_APPROVAL_PENDING"),
        Map.entry("4", "CFO_APPROVED"),
        Map.entry("5","ADMIN_APPROVAL_PENDING"),
        Map.entry("6", "APPROVED"),
        Map.entry("7", "REJECTED")
        );

        // 2. Save permissions if not exist & map for quick lookup
        Map<String, Permission> savedPermissions = new HashMap<>();
        for (var entry : permissionsMap.entrySet()) {
            String name = entry.getKey();
            String desc = entry.getValue();

            Permission permission = permissionRepository.findByName(name);
            if (permission == null) {
                permission = new Permission(name, desc);
                permission = permissionRepository.save(permission);
            }
            savedPermissions.put(name, permission);
        }

        // 3. Define roles and their permission keys
        Map<String, List<String>> rolePermissionsMap = Map.of(
            "Human Resource", List.of(
                "create_job_post",
                "view_all_applications",
                "create_longlist",
                "view_longlist",
                "schedule_interview",
                  "create_rem_form"
            ),
            "Line Manager", List.of(
                "create_job_requisition",
                "edit_job_description",
                "view_longlist",
                "view_all_applications",
                "create_shortlist",
                "view_approvals",
                   "approve_approvals"

                
            ),
            "HR Executive", List.of(
                "approve_requisition",
                "view_all_applications",
                "view_approvals",
                "approve_approvals"

                
            ),
            "CFO", List.of(
                "view_approvals",
                "approve_approvals"
            ),
            "Admin", new ArrayList<>(savedPermissions.keySet()) 
        );

        // 4. Save roles and assign permissions
        for (var roleEntry : rolePermissionsMap.entrySet()) {
            String roleName = roleEntry.getKey();
            List<String> permKeys = roleEntry.getValue();

            Role role = roleRepository.findByName(roleName);
            if (role == null) {
                role = new Role();
                role.setRoleName(roleName);
            }

            Set<Permission> permsForRole = new HashSet<>();
            if (roleName.equals("Admin")) {
                // Admin gets all permissions
                permsForRole.addAll(savedPermissions.values());
            } else {
                for (String permKey : permKeys) {
                    Permission p = savedPermissions.get(permKey);
                    if (p != null) {
                        permsForRole.add(p);
                    }
                }
            }
            role.setPermissions(permsForRole);
            roleRepository.save(role);
        }
        
        for(var roleEntry:statusMap.entrySet()){
             String name = roleEntry.getValue();
             OfferLetterStatus offerStatus = offerLetterStatusRepository.findByName(name).orElse(null);
             RemFormStatus remFormStatus = remFormStatusRepository.findByName(name).orElse(null); 

             if(offerStatus==null){
                offerStatus = new OfferLetterStatus();
                offerStatus.setName(name);
                offerLetterStatusRepository.save(offerStatus);
             }
             if(remFormStatus==null){
               remFormStatus = new RemFormStatus();
               remFormStatus.setName(name);
               remFormStatusRepository.save(remFormStatus);
             }
        }
        
    }
}

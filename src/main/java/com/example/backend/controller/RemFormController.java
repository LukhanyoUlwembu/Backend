package com.example.backend.controller;

import com.example.backend.model.RemForm;
import com.example.backend.service.HrServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rem-forms")
public class RemFormController {

    @Autowired
    private HrServiceImplementation hr;

    @PostMapping("/{jobId}")
    public RemForm createRemForm(@RequestBody RemForm remForm, @PathVariable long jobId) {
        return hr.createRemForm(remForm, jobId);
    }

    @GetMapping
    public List<RemForm> getAllRemForms() {
        return hr.getAllRemForms();
    }

    @GetMapping("/{id}")
    public RemForm getRemForm(@PathVariable long id) {
        return hr.getRemFormById(id);
    }

    //Approval Endpoints
//@PreAuthorize("hasRole('Line Manager')")
@PutMapping("/{id}/approve-manager")
public RemForm approveRemFormByManager(
        @PathVariable Long id,
        @RequestBody Map<String, String> body) {

    String userEmail = body.get("userEmail");
    return hr.approveRemFormByManager(id, userEmail);
}

//@PreAuthorize("hasRole('Human Resource')")
@PutMapping("/{id}/approve-hr")
public RemForm approveRemFormByHr(
        @PathVariable Long id,
        @RequestBody Map<String, String> body) {

    String userEmail = body.get("userEmail");
    return hr.approveRemFormByHr(id, userEmail);
}

//@PreAuthorize("hasRole('CFO')")
@PutMapping("/{id}/approve-cfo")
public RemForm approveRemFormByCfo(
        @PathVariable Long id,
        @RequestBody Map<String, String> body) {

    String userEmail = body.get("userEmail");
    return hr.approveRemFormByCfo(id, userEmail);
}

// Rejection
@PutMapping("/{id}/reject")
public RemForm rejectRemForm(
        @PathVariable Long id,
        @RequestBody Map<String, String> body) {

    String userEmail = body.get("userEmail");
    return hr.rejectRemForm(id, userEmail);
}


}

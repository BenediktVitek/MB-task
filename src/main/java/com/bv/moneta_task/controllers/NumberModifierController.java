package com.bv.moneta_task.controllers;

import com.bv.moneta_task.services.NumberModifierService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/numbers")
public class NumberModifierController {

    @Autowired
    NumberModifierService numberModifierService;

    @GetMapping("/modify")
    public ResponseEntity<?> modifyInput(@RequestParam("inputNumber") long inputNumber) {
        try {
            return ResponseEntity.ok(numberModifierService.getResult(inputNumber));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(String.format("Number %d cannot be modified", inputNumber));
        }
    }
}

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
    public ResponseEntity<Long> modifyInput(@RequestParam("inputNumber") long inputNumber) {
        return ResponseEntity.ok(numberModifierService.getResult(inputNumber));
    }
}

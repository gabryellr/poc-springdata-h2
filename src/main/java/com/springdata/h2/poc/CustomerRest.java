package com.springdata.h2.poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerRest {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> find(@PathVariable Long id){
         Optional<Customer> customerOptional = customerRepository.findById(id);
         if(customerOptional.isPresent()){
             return new ResponseEntity<Customer>(customerOptional.get(), HttpStatus.OK);
         }else{
             return new ResponseEntity<>(customerOptional.get(), HttpStatus.NOT_FOUND);
         }
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll(){
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer){
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
}

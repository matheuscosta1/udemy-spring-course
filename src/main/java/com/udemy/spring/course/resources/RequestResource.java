package com.udemy.spring.course.resources;

import com.udemy.spring.course.domain.Request;
import com.udemy.spring.course.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/requests")
public class RequestResource {

    @Autowired
    private RequestService service;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Request> find(@PathVariable Integer id){

        Request obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }

}
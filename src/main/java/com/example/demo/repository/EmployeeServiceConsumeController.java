package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/rest/")
public class EmployeeServiceConsumeController {
   //private static final String URL_Rest = "https://opentdb.com/api.php?amount=10&category=18&difficulty=easy&type=multiple";
   private static final String URL_GET = "http://localhost:9000/allemployees";
    private static final String URL_POST = "http://localhost:9000/save";
    private static final String URL_PUT= "http://localhost:9000/update";
    private static final String URL_DELETE= "http://localhost:9000/delete/{id}";
    @Autowired
   private RestTemplate restTemplate;

    @RequestMapping(value = "/all")
    public String getEmployeesList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
       // String body = restTemplate.exchange(URL_Rest, HttpMethod.GET, entity, String.class).getBody();
        //body.forEach(System.out::println);
        return restTemplate.exchange(URL_GET, HttpMethod.GET, entity, String.class).getBody();

    }

    @PostMapping(value = "/save",consumes = "application/json")
    public String saveEmployee(@RequestBody Employee employee) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Employee> entity = new HttpEntity<Employee>(employee,headers);
        //Employee employee1 = restTemplate.postForObject(URL_POST, employee, Employee.class);
        return restTemplate.exchange(URL_POST, HttpMethod.POST, entity, String.class).getBody();
    }

    @PutMapping(value = "/update",consumes = "application/json")
    public String updateEmployee( @RequestBody Employee employee) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Employee> entity = new HttpEntity<Employee>(employee,headers);
        return restTemplate.exchange(URL_PUT, HttpMethod.PUT, entity, String.class).getBody();
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteEmployee( @PathVariable("id") final String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Employee> entity = new HttpEntity<Employee>(headers);
        ResponseEntity<String> respone = restTemplate.exchange("http://localhost:9000/delete/"+id, HttpMethod.DELETE, entity, String.class);
        return respone.getBody();
    }
}

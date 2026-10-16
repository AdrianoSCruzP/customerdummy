package com.demo.example.mscustomerdummy.service.impl;

import com.demo.example.mscustomerdummy.model.Customer;
import com.demo.example.mscustomerdummy.repository.CustomerRepository;
import com.demo.example.mscustomerdummy.request.CustomerRequest;
import com.demo.example.mscustomerdummy.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Override
  public Flux<Customer> listCustomers() {
    return customerRepository.findAll();
  }

  @Override
  public Mono<Customer> getCustomer(String id) {
    return customerRepository.findById(id)
        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer no encontrado")));
  }

  @Override
  public Mono<Void> deleteCustomer(String id) {
    return customerRepository.findById(id)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer no encontrado")))
            .flatMap(customerRepository::delete)
            .then();
  }

  @Override
  public Mono<Customer> createCustomer(CustomerRequest customerRequest) {
    Customer customer = Customer.builder()
            .nombre(customerRequest.nombre())
            .apellido(customerRequest.apellido())
            .correo(customerRequest.correo())
            .dni(customerRequest.dni())
            .build();
    return customerRepository.save(customer);
  }

  @Override
  public Mono<Customer> updateCustomerPartial(String id, CustomerRequest customerRequest) {
    return customerRepository.findById(id)
            .flatMap(customer -> {
              if (customerRequest.nombre() != null) customer.setNombre(customerRequest.nombre());
              if (customerRequest.apellido() != null) customer.setApellido(customerRequest.apellido());
              if (customerRequest.correo() != null) customer.setCorreo(customerRequest.correo());
              if (customerRequest.dni() != null) customer.setDni(customerRequest.dni());

              return customerRepository.save(customer);
            })
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer no encontrado")));
  }

}

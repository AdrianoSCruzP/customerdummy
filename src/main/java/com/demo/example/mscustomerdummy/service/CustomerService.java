package com.demo.example.mscustomerdummy.service;

import com.demo.example.mscustomerdummy.model.Customer;
import com.demo.example.mscustomerdummy.request.CustomerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
  public Flux<Customer> listCustomers();
  public Mono<Customer> getCustomer(String id);
  public Mono<Void> deleteCustomer(String id);
  public Mono<Customer> createCustomer(CustomerRequest customerRequest);
  public Mono<Customer> updateCustomerPartial (String id, CustomerRequest customerRequest);
}

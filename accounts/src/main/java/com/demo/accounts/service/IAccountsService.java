package com.demo.accounts.service;

import com.demo.accounts.dto.CustomerDto;
import org.springframework.stereotype.Service;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetch(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}

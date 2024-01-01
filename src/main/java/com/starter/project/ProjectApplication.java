package com.starter.project;

import com.starter.project.dao.*;
import com.starter.project.model.*;
import com.starter.project.model.enums.LanguageEnum;
import com.starter.project.model.enums.RoleEnum;
import com.starter.project.service.AccountService;
import com.starter.project.service.AgencyService;
import com.starter.project.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {
    @Autowired
    RequestService requestService;

    @Autowired
    AccountService accountService;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    AgencyService agencyService;

    @Autowired
    CardTypeDao cardTypeDao;

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //createRequest();

    }

    @Bean
    CommandLineRunner commandLineRunner(
            RequestHistoryDao requestHistoryDao,
            CardDao cardDao,AccountDao accountDao,
            CardTypeDao cardTypeDao,RoleDao roleDao,
            UserDao userDao, PasswordEncoder passwordEncoder,
            RequestDao requestDao,RequestStatusDao requestStatusDao,
            AgencyDao agencyDao){
        return args -> {
            Role role = new Role(RoleEnum.ADMIN);
            Set<Role> roles = new HashSet<>();
            roleDao.save(role);
            roles.add(role);
            for (long i = 0L; i<5; i++) {
                CardType cardType = new CardType();
                cardType.setCode("1234"+i);
                cardType.setLabel("master card"+i);
                cardTypeDao.save(cardType);

                Agency agency = new Agency();
                agency.setCode("123454"+i);
                agency.setCity("NKCCcf"+i);
                agency.setLabel("BMDDcf"+i);
                agency.setAreaCode("22cf222"+i);
                agencyDao.save(agency);

                User user = new User();
                user.setEmail("sidi1@fmail.com"+i);
                user.setPassword(passwordEncoder.encode("admin"));
                user.setUsername("sidi1"+i);
                user.setRoles(roles);
                user.setAgency(agency);
                userDao.save(user);

                Account account = new Account();
                account.setAccountNumber("12345678"+i);
                account.setAgency(agency);
                accountDao.save(account);

                Card card = new Card();
                card.setAccount(account);
                card.setCardNum("12345"+i);
                card.setCardType(cardType);
                card.setActivationDate(new Date());
                cardDao.save(card);

                RequestStatus requestStatus = new RequestStatus();
                requestStatus.setStatusCode(RequestStatusEnum.ACCEPTED);
                requestStatus.setFrLabel("fr");
                requestStatus.setId(i);
                requestStatusDao.save(requestStatus);

                Set<RequestHistory> requestHistorySet = new HashSet<>();
                RequestHistory requestHistory = new RequestHistory();
                requestHistory.setStatus(requestStatus);
                requestHistory.setStartDate(new Date());
                requestHistorySet.add(requestHistory);
                requestHistoryDao.save(requestHistory);

                Request request = new Request();
                request.setAccount(account);
                request.setCard(card);
                request.setAgency(agency);
                request.setCreationDate(new Date());
                request.setSmsLang(LanguageEnum.FR);
                request.setHistories(requestHistorySet);
                requestDao.save(request);
            }
        };
    }

    private void createRequest() {
        var cardType = new CardType();
        cardType.setCode("code");
        cardType.setLabel("label");
        cardTypeDao.save(cardType);
        Agency agency = new Agency();
        agency.setCode("code");
        agency.setLabel("label");
        agencyService.save(agency);
        Customer customer = new Customer();
        customer.setBirthDate(new Date());
        customer.setAddress("120 avenue roger salengro");
        customer.setFirstName("first name");
        customer.setLastName("last name");
        customer.setNni("nni");
        customer.setTitle("title");
        customer.setPhoneNumber("11");
        customerDao.save(customer);
        Account account = new Account();
        account.setAccountNumber("accountNumber");
        account.setAgency(agency);
        account.setCustomer(customer);
        accountService.save(account);
        Request request = new Request();
        request.setAccount(account);
        request.setCreationDate(new Date());
        request.setAgency(agency);
        request.setNameOnCard("name on card");
        request.setCardType(cardType);
        request.setSmsLang(LanguageEnum.FR);
        requestService.save(request);
    }

}

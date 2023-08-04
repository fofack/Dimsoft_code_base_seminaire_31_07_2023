package com.proof.concepttest.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.proof.concept.BackendApplication;
import com.proof.concept.beans.User;
import com.proof.concept.services.UsersService;

@SpringBootTest(classes=BackendApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext
public class ManagementOfUserAccountsOnThePlatformFeature {
    /*@Autowired
	private TestRestTemplate restTemplate;

    @LocalServerPort
	private int port;
    
    @BeforeEach
    public void before() {
        usersService.deleteAll();
    }

    @AfterEach
    public void after() {
        
    }

    @When("One asks for the list of users")
    public void oneAsksForTheListOfUsers() {
        ResponseEntity<List<User>> users = this.restTemplate.exchange(
            "http://localhost:" + port + "/users/getAllUsers", 
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<User>>() {}
        );
        this.currentUsersInDb = users.getBody();
        System.out.println(this.currentUsersInDb);
    }

    @Then("He gets the list that contains the following users")
    public void heGetsTheListThatContainsTheFollowingUsers(List<UserInTest> users) {
        assertEquals(users.size(), this.currentUsersInDb.size());
        for (UserInTest userInTest : users) {
            List<User> result = this.currentUsersInDb.stream()
                .filter(user -> user.getUserEmail().equalsIgnoreCase(userInTest.getEmail()))
                .collect(Collectors.toList());
            assertNotNull(result);
            assertFalse(result.isEmpty());
        }
    }*/
}

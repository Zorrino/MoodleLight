package fr.uca.springbootstrap.cucumber;

import fr.uca.springbootstrap.SpringIntegration;
import fr.uca.springbootstrap.controllers.AuthController;
import fr.uca.springbootstrap.models.ERole;
import fr.uca.springbootstrap.models.Module;
import fr.uca.springbootstrap.models.Role;
import fr.uca.springbootstrap.models.User;
import fr.uca.springbootstrap.repository.ModuleRepository;
import fr.uca.springbootstrap.repository.RoleRepository;
import fr.uca.springbootstrap.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterTeacherStepDefs {
    private SpringIntegration springIntegration = new SpringIntegration();
    private static final String PASSWORD = "password";
    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthController authController;

    @Autowired
    PasswordEncoder encoder;


    @Given("a teacher with login {string}")
    public void aTeacherWithLogin(String arg0) {
        User user = userRepository.findByUsername(arg0).
                orElse(new User(arg0, arg0 + "@test.fr", encoder.encode(PASSWORD)));
        user.setRoles(new HashSet<Role>() {{
            add(roleRepository.findByName(ERole.ROLE_TEACHER).
                    orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
        }});
        userRepository.save(user);
    }

    @And("a module named {string}")
    public void aModuleNamed(String arg0) {
        Module module = moduleRepository.findByName(arg0).orElse(new Module(arg0));
        module.setParticipants(new HashSet<>());
        moduleRepository.save(module);
    }

    @When("{string} registers to module {string}")
    public void registersToModule(String arg0, String arg1) throws Exception {
        Module module = moduleRepository.findByName(arg1).get();
        User user = userRepository.findByUsername(arg0).get();
        String jwt = authController.generateJwt(arg0, PASSWORD);

        springIntegration.executePost("http://localhost:8080/api/module/" + module.getId() + "/participants/" + user.getId(), jwt); //TODO do the function
    }

    @Then("last request status is {int}")
    public void isRegisteredToModule(int status) {
        assertEquals(status, latestHttpResponse.getStatusLine().getStatusCode()); //TODO recuperer la derniere reponse http
    }

    @Then("{string} is registered to module {string}")
    public void isRegisteredToModule(String arg0, String arg1) {
        Module module = moduleRepository.findByName(arg1).get();
        User user = userRepository.findByUsername(arg0).get();
        assertTrue(module.getParticipants().contains(user));
    }

    @And("{string} is not registered to module {string}")
    public void isNotRegisteredToModule(String arg0, String arg1) {
        Module module = moduleRepository.findByName(arg1).get();
        User user = userRepository.findByUsername(arg0).get();
        assertFalse(module.getParticipants().contains(user));
    }
}


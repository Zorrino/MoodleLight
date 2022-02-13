package fr.uca.springbootstrap.controllers;


import fr.uca.springbootstrap.models.Resource;
import fr.uca.springbootstrap.models.User;
import fr.uca.springbootstrap.repository.ModuleRepository;
import fr.uca.springbootstrap.repository.ResourceRepository;
import fr.uca.springbootstrap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/module/resource")
public class ResourceController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    ResourceRepository resourceRepository;

    @GetMapping("/{resourceName}/id")
    public ResponseEntity<String> getId(@PathVariable String resourceName){
        Resource resource = resourceRepository.findByName(resourceName).orElse(null);
        if (resource==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body("{\"id\" : "+resource.getId()+"}");
    }

    @GetMapping("/{resourceId}/name")
    public ResponseEntity<String> getName(@PathVariable Long resourceId){
        Resource resource = resourceRepository.findById(resourceId).orElse(null);
        if (resource==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body("{\"name\" : "+resource.getName()+"}");
    }

    @GetMapping("/{resourceId}/users")
    public ResponseEntity<String> getUsers(@PathVariable Long resourceId){
        Resource resource = resourceRepository.findById(resourceId).orElse(null);
        if (resource==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body("[" + resource.getParticipants().stream().map(User::toString).reduce("", (subtotal, element) -> subtotal + element + ",") + "]");
    }


}

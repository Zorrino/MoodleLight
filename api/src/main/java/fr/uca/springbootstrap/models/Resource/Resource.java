package fr.uca.springbootstrap.models.Resource;


import fr.uca.springbootstrap.models.Role;
import fr.uca.springbootstrap.models.Module;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "resource",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public abstract class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "resource_visibility",
            joinColumns = @JoinColumn(name = "resource_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> visibility;

    @NotBlank
    private String description;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    public Resource() {
        this.visibility = new HashSet<>();
    }

    public Resource(String name,String description) {
        this.name = name;
        this.description = description;
        this.visibility = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Set<Role> getVisibility() {
        return visibility;
    }

    public void addVisibility(Role r){
        visibility.add(r);
    }

    public void setVisibility(Set<Role> visibility) {
        this.visibility = visibility;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract String getContent();
    @Override
    public String toString() {
        return "{\"id\":" + id + ", \"name\":" + name + "}";
    }
}

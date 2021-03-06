package fr.uca.springbootstrap.payload.request;

import javax.validation.constraints.NotBlank;

public class ModuleRequest {
    @NotBlank
    private String name;

    public ModuleRequest() {
    }

    public ModuleRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

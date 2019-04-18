package org.plc.interview.s4.controllers;

import org.plc.interview.s4.controllers.dto.RegistryDTO;
import org.plc.interview.s4.domain.Registry;
import org.plc.interview.s4.domain.RegistryKey;
import org.plc.interview.s4.domain.repository.RegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistryController {
    @Autowired
    private RegistryRepository registryRepository;

    @PostMapping(value = "/registry")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Registry save(@RequestBody RegistryDTO registryDTO) {
        Registry registry = new Registry();
        registry.setId(new RegistryKey(registryDTO.getStudentId(), registryDTO.getCourseId()));
        return registryRepository.save(registry);
    }
}

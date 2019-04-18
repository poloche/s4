package org.plc.interview.s4.domain.repository;

import org.plc.interview.s4.domain.Registry;
import org.plc.interview.s4.domain.RegistryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistryRepository extends JpaRepository<Registry, RegistryKey> {
}

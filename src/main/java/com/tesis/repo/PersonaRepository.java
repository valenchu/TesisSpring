package com.tesis.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tesis.dto.PersonaRecord;
import com.tesis.entidad.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

	@Query("SELECT new com.tesis.dto.PersonaRecord(p.id, p.cuit, p.tipoPersona) FROM Persona p WHERE p.cuit = :cuit")
	Optional<PersonaRecord> findByCuit(@Param("cuit") String cuit);
}
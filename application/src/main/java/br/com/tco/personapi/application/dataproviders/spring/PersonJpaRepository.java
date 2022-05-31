package br.com.tco.personapi.application.dataproviders.spring;

import br.com.tco.personapi.application.dataproviders.spring.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJpaRepository extends JpaRepository<PersonEntity, Integer> {}

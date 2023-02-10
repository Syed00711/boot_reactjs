package com.cerner.avaya.rad.env.local.agents;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AgentRepository extends PagingAndSortingRepository<Agent, Long> {

}
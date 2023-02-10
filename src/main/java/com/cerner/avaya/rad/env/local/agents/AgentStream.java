package com.cerner.avaya.rad.env.local.agents;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
* Simulates a stream by pumping random changes to all agents to all registered WebSocket clients.
*/
@EnableScheduling
@Component
public class AgentStream {

    Logger log = LoggerFactory.getLogger(AgentStream.class);

    private Random rand = new Random();

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedDelay = 5000)
    public void simulateStream() {

        agentRepository.findAll().forEach(agent -> {
            if (rand.nextInt(10) >= 8) {
                String state = agent.state;
                if (state.equals("AVAIL")) {
                    state = "ACDIN";
                } else {
                    state = "AVAIL";
                }

                agent.state = state;
                agent.stateTime = System.currentTimeMillis();

                agentRepository.save(agent);
                template.convertAndSend("/topic/agents", agent);
            }
        });
    }
}
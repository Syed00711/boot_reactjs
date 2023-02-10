import React, { Component } from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import './App.css';
import Dashboard from './Dashboard';
import GroupFilter from './GroupFilter';

class App extends Component {

    constructor(props) {
        super(props);

        this.state = {
            agents: new Map(),
            // TODO: this needs to be fetched from a controller, from a config file
            groups: [
                { id: 1, name: "Time Travel", skills: ["time travel", "adventure"] },
                { id: 2, name: "the 60s", skills: ["the 60s"] },
                { id: 3, name: "Driving", skills: ["driving"] },
            ],
            activeGroupId: null,
            now: Date.now(),
        };
        this.stompClient = null;

        this.handleAgentMessage = this.handleAgentMessage.bind(this);
        this.onFilterSelect = this.onFilterSelect.bind(this);
    }

    handleAgentMessage(data) {
        const agent = JSON.parse(data.body);
        const { agents } = this.state;

        agents.set(agent.userName, agent);
        this.setState({ agents: agents });
    }

    subscribeToAgentsTopic() {
        const socket = new SockJS('/ws');
        const sc = Stomp.over(socket);
        this.stompClient = sc;

        sc.connect({}, (frame) => {
            sc.subscribe('/topic/agents', this.handleAgentMessage);
        });
    }

    toAgentMap(agents) {
        var map = new Map();

        agents.forEach(agent => {
            map.set(agent.userName, agent);
        });

        return map;
    }

    onFilterSelect(groupId) {
        this.setState({ activeGroupId: parseInt(groupId) });
    }

    componentDidMount() {
        fetch('/agents')
            .then(response => response.json())
            .then(data => this.setState({ agents: this.toAgentMap(data._embedded.agents) }))
            .then(this.subscribeToAgentsTopic());

        setInterval(() => this.setState({ now: Date.now() }), 1000);
    }

    componentWillUnmount() {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        console.log("Disconnected WS");
    }

    render() {
        const { groups } = this.state;
        return (
            <div>
                <GroupFilter groups={groups} onSelect={this.onFilterSelect} />
                <Dashboard {...this.state} />
            </div>
        )
    }
}

export default App;
import React, { Component } from 'react';

class AgentTable extends Component {

    render() {
        const { agents, groups, activeGroupId, now } = this.props;

        function doFilter(agent) {
            const activeGroup = groups.find(({ id }) => id === activeGroupId);

            if (activeGroup) {
                const matchingSkills = agent.skills.split(',')
                    .map(s => s.trim())
                    .filter(skill => activeGroup.skills.includes(skill))
                    .length;
                return matchingSkills > 0;
            } else {
                return true;
            }
        }

        return (
            <div className="AgentTable">
                <table>
                    <tbody>
                        <tr>
                            <th>Name</th><th>Extension</th><th>Skill</th><th>State</th><th>Reason</th><th>Time</th>
                        </tr>
                        {[...agents.values()]
                            .filter(agent => doFilter(agent))
                            .map(agent =>
                                <tr key={agent.userName}>
                                    <td>{agent.name}</td>
                                    <td>{agent.extension}</td>
                                    <td>{agent.skills}</td>
                                    <td>{agent.state}</td>
                                    <td>{agent.reason}</td>
                                    <td>{Math.ceil((now - agent.stateTime) / 1000)}s</td>
                                </tr>)}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default AgentTable;

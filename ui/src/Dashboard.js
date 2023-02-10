import React, { Component } from 'react';
import AgentTable from './AgentTable';

class Dashboard extends Component {

  render() {
    return (
      <div className="Dashboard">
        <AgentTable {...this.props} />
      </div>
    );
  }
}

export default Dashboard;

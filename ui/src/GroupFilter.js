import React, { Component } from 'react';

class GroupFilter extends Component {

    render() {
        const { groups, onSelect } = this.props;

        return (
            <div>
                <div>Select a Group</div>
                <select onChange={e => onSelect(e.target.value)}>
                    <option></option>
                    {groups.map(group =>
                        <option key={group.id} value={group.id}>
                            {group.name}
                        </option>
                    )}
                </select>
            </div>
        );
    }
}

export default GroupFilter;
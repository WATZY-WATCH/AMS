import React from 'react'
import ReactDOM from 'react-dom'

class UserHome extends React.Component {
    render() {
        return <div className="main">메인페이지</div>;
    }
}

ReactDOM.render(<UserHome/>, document.getElementById('root'));
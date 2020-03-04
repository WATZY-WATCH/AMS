import React, { Fragment } from 'react';
import ReactDOM from 'react-dom';
import request from 'superagent';

export default class Calendar extends React.Component {
    constructor(props) {
    	super(props);
        let typeReg = /monthly/gi;
        if(props.type && typeReg.test(props.type.toLowerCase())) {
            this.type = "MONTHLY";
        } else this.type = "WEEKLY";

        let date = new Date();

        this.groupId = new URLSearchParams(location.search).get("groupId");

        this.state = {
            data: null,
            year: date.getFullYear(),
            month: date.getMonth() + 1
        }
    }

    UNSAFE_componentWillMount() {
        this.getCalendar(0);
    }

    getCalendar(term) {
        let elementToken = document.querySelector('meta[name="_csrf"]');
        let token = elementToken && elementToken.getAttribute("content");
        let elementHeader = document.querySelector('meta[name="_csrf_header"]');
        let header = elementHeader && elementHeader.getAttribute("content");

        const data = {groupId : this.groupId, year:this.state.year, month: this.state.month, term: term}

        request.post("/group/getCalendar")
                .set(header, token)
                .set("Content-Type", 'application/json')
                .send(JSON.stringify(data))
                .end((err, res) => {
                    this.setCalendar(err, res)
                })
    }

    setCalendar(err, res) {
        let ret = JSON.parse(res.text);
        if(err) {
            console.log('에러가 발생했습니다. 잠시 후 시도해주세요.');
            return;
        }
        this.setState({
            data: ret,
            year: ret.year,
            month: ret.month
        })
    }

    render() {
        if(!this.state.data) {
            return <div className="loading">로딩중</div>
        }

        let cal = [],
            weeks = this.state.data.weeks,
            firstDateStr,
            lastDateStr,
            firstDate,
            lastDate,
            dayStr = ["일", "월", "화", "수", "목", "금", "토"];

        firstDateStr = this.state.data.firstDate.year + "-"
                        + this.state.data.firstDate.monthValue.zf(2)+ "-"
                        + this.state.data.firstDate.dayOfMonth.zf(2);
        lastDateStr = this.state.data.lastDate.year + "-"
                        + this.state.data.lastDate.monthValue.zf(2)+ "-"
                        + this.state.data.lastDate.dayOfMonth.zf(2);
        
        firstDate = new Date(firstDateStr);
        lastDate = new Date(lastDateStr);

        let dayList = dayStr.map(v=> {
            return <span className="day-name">{v}</span>
        })
        for(let i=0; i<weeks; i++) {
            let tr = [];
            for(let j=0; j<7; j++) {
                let today = firstDate.plusDays(i*7 + j).getDate();
                let className = "day day-"+(j+1);
                let td = <div className={className}>
                    <div className="day-label">{today}</div>
                    <div className="days"></div>
                </div>;
                tr.push(td);
            }
            let className = "weeks " + "week-"+(i+1);
            cal.push(<div className={className}>{tr}</div>);
        }
        return <Fragment>
            <div>
                <div>
                    <div>
                    <button onClick={e=>this.getCalendar(-1)}>&laquo;</button>
					<span className="year-name">{this.state.year}</span> - <span className="month-name">{this.state.month}</span>
					<button onClick={e=>this.getCalendar(1)}>&raquo;</button>
                    </div>
                </div>
                <div>{dayList}</div>
            </div>
            <div>{cal}</div>
        </Fragment>;
    }
}

ReactDOM.render(<Calendar />, document.getElementById("calendar"));

Date.prototype.plusDays = function (day) {
	let today = this,
		next = today.getTime() + 24 * 60 * 60 * 1000 * day;
	return new Date(next);
}

Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};

String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};
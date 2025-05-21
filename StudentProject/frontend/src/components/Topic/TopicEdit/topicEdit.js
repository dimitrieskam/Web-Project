import React from 'react';
import {useNavigate} from 'react-router-dom';

const TopicEdit = (props) => {

    const history = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: props.topic.name || "",
        formDate: props.topic.formDate || "",
        toDate: props.topic.toDate || "",
        groupCount: props.topic.groupCount || "",
        membersPerGroup: props.topic.membersPerGroup || "",
        subjectId: props.topic.subject?.id || ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
    
        const name = formData.name !== "" ? formData.name : props.topic.name;
        const fromDate = formData.fromDate !== "" ? formData.fromDate : props.topic.fromDate;
        const toDate = formData.toDate !== "" ? formData.toDate : props.topic.toDate;
        const groupCount = formData.groupCount !== "" ? formData.groupCount : props.topic.groupCount;
        const membersPerGroup = formData.membersPerGroup !== "" ? formData.membersPerGroup : props.topic.membersPerGroup;
        const subjectId = formData.subjectId !== "" ? formData.subjectId : props.topic.subject.id;
    
        props.onEditTopic(props.topic.id, name, fromDate, toDate, groupCount, membersPerGroup, subjectId);
        history("/topics");
    };
    

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Topic name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               placeholder={props.topic.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">From Date</label>
                        <input type="date"
                               className="form-control"
                               id="fromDate"
                               name="fromDate"
                               placeholder={props.topic.fromDate}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="toDate">To Date</label>
                        <input
                            type="date"
                            className="form-control"
                            id="toDate"
                            name="toDate"
                            defaultValue={props.topic.toDate}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="groupCount">Group Count</label>
                        <input
                            type="number"
                            className="form-control"
                            id="groupCount"
                            name="groupCount"
                            defaultValue={props.topic.groupCount}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="membersPerGroup">Members Per Group</label>
                        <input
                            type="number"
                            className="form-control"
                            id="membersPerGroup"
                            name="membersPerGroup"
                            defaultValue={props.topic.membersPerGroup}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="subjectId">Subject ID</label>
                        <input
                            type="text"
                            className="form-control"
                            id="subjectId"
                            name="subjectId"
                            defaultValue={props.topic.subject?.id}
                            onChange={handleChange}
                        />
                    </div>
                    
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default TopicEdit;

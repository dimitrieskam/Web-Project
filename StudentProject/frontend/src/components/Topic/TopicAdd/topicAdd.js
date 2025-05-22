import React from 'react';
import {useNavigate} from 'react-router-dom';

const TopicAdd = (props) => {

    const navigate = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        fromDate: "",
        toDate: "",
        groupCount: "",
        membersPerGroup: "",
        subjectId: ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name;
        const fromDate = formData.fromDate;
        const toDate = formData.toDate;
        const groupCount = formData.groupCount;
        const membersPerGroup = formData.membersPerGroup;
        const subjectId = formData.subjectId;

        props.onAddProfessor(name,fromDate, toDate, parseInt(groupCount), parseInt(membersPerGroup), subjectId);
        navigate("/topics");
    }

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
                               required
                               placeholder="Enter topic name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">From Date</label>
                        <input type="date"
                               className="form-control"
                               id="fromDate"
                               name="fromDate"
                               placeholder="fromDate"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">To Date</label>
                        <input type="date"
                               className="form-control"
                               id="toDate"
                               name="toDate"
                               placeholder="toDate"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Group Count</label>
                        <input type="number"
                               className="form-control"
                               id="groupCount"
                               name="groupCount"
                               placeholder="Enter number of groups"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Members per Group</label>
                        <input type="number"
                               className="form-control"
                               id="membersPerGroup"
                               name="membersPerGroup"
                               placeholder="Enter number of members"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="subjectId">Subject</label>
                        <select className="form-control" id="subjectId" name="subjectId" required onChange={handleChange}>
                            <option value="">Select Subject</option>
                            {props.subjects?.map(subject => (
                                <option key={subject.id} value={subject.id}>{subject.name}</option>
                            ))}
                        </select>
                    </div>
                    
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default TopicAdd;

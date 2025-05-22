import React from 'react';
import {useNavigate} from 'react-router-dom';

const SubjectEdit = (props) => {

    const history = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: props.subject.name || "",
        code: props.subject.code || "",
        students: props.subject.students.map(s => s.id).join(", ") || "",
        professors: props.subject.professors.map(p => p.id).join(", ") || ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.subject.name;
        const code = formData.code !== "" ? formData.code : props.subject.code;
        // Parse comma-separated input to array of numbers
        const students = formData.students.split(",").map(id => parseInt(id.trim()));
        const professors = formData.professors.split(",").map(id => parseInt(id.trim()));
                
        props.onEditSsubject(props.subject.id, name, code, students, professors);
        history.push("/subjects");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Subject name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               placeholder={props.subject.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">Code</label>
                        <input type="text"
                               className="form-control"
                               id="code"
                               name="code"
                               placeholder={props.subject.code}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">Students (IDs, comma-separated)</label>
                        <input type="text"
                               className="form-control"
                               id="students"
                               name="students"
                               value={formData.students}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Professors (IDs, comma-separated)</label>
                        <input type="text"
                               className="form-control"
                               id="professor"
                               name="porfessor"
                               value={formData.professors}
                               onChange={handleChange}
                        />
                    </div>
                    
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default SubjectEdit;

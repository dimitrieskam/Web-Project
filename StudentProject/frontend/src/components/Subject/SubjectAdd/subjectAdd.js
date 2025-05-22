import React from 'react';
import {useNavigate} from 'react-router-dom';

const SubjectAdd = (props) => {

    const navigate = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        code: "",
        students: "",
        professors: ""
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
        const code = formData.code;
        const students = formData.students
            .split(',')
            .map(s => parseInt(s.trim()))
            .filter(n => !isNaN(n));

        const professors = formData.professors
            .split(',')
            .map(p => parseInt(p.trim()))
            .filter(n => !isNaN(n));

        props.onAddSubject(name, code, students, professors);
        navigate("/subjects");
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
                               required
                               placeholder="Enter subject name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">Code</label>
                        <input type="text"
                               className="form-control"
                               id="code"
                               name="code"
                               placeholder="Code"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">Students (IDs, comma-separated)</label>
                        <input type="text"
                               className="form-control"
                               id="students"
                               name="students"
                               placeholder="e.g. 1, 2, 3"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Professors (IDs, comma-separated)</label>
                        <input type="text"
                               className="form-control"
                               id="professors"
                               name="professors"
                               placeholder="e.g. 1, 2, 3"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default SubjectAdd;

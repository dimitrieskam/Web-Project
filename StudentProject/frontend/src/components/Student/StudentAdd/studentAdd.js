import React from 'react';
import {useNavigate} from 'react-router-dom';

const StudentAdd = (props) => {

    const navigate = useNavigate();
    const [formData, updateFormData] = React.useState({
        index: "",
        name: "",
        lastname: "",
        username: "",
        email: ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const index = formData.index;
        const name = formData.name;
        const lastname = formData.lastname;
        const username = formData.username;
        const email = formData.email;

        props.onAddStudent(index,name, lastname, username, email);
        navigate("/students");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="index">Index</label>
                        <input type="text"
                               className="form-control"
                               id="index"
                               name="index"
                               required
                               placeholder="Enter index"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="name">Student name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder="Enter student name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="lastname">Lastname</label>
                        <input type="text"
                               className="form-control"
                               id="lastname"
                               name="lastname"
                               placeholder="Lastname"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="username">Username</label>
                        <input type="text"
                               className="form-control"
                               id="username"
                               name="username"
                               placeholder="Username"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="email">Email</label>
                        <input type="text"
                               className="form-control"
                               id="email"
                               name="email"
                               placeholder="Email"
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

export default StudentAdd;

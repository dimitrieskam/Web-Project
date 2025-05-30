import React from 'react';
import {useNavigate} from 'react-router-dom';

const ProfessorAdd = (props) => {

    const navigate = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        surname: "",
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
        const name = formData.name;
        const surname = formData.surname;
        const email = formData.email;

        props.onAddProfessor(name, surname, email);
        navigate("/professors");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Professor name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder="Enter professor name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">Surname</label>
                        <input type="text"
                               className="form-control"
                               id="surname"
                               name="surname"
                               placeholder="Surname"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Email</label>
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

export default ProfessorAdd;

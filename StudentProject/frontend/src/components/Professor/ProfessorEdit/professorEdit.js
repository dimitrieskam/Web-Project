import React from 'react';
import {useNavigate} from 'react-router-dom';

const ProfessorEdit = (props) => {

    const history = useNavigate();
    const [formData, updateFormData] = React.useState({
        "name": "",
        "surname": "",
        "email": ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.profesor.name;
        const surname = formData.surname !== "" ? formData.surname : props.profesor.surname;
        const email = formData.email !== "" ? formData.email : props.profesor.email;
        
        props.onEditProfessor(props.profesor.id, name, surname, email);
        history("/professors");
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
                               placeholder={props.profesor.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">Surname</label>
                        <input type="text"
                               className="form-control"
                               id="surname"
                               name="surname"
                               placeholder={props.profesor.surname}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Email</label>
                        <input type="text"
                               className="form-control"
                               id="email"
                               name="email"
                               placeholder={props.profesor.email}
                               onChange={handleChange}
                        />
                    </div>
                    
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default ProfessorEdit;

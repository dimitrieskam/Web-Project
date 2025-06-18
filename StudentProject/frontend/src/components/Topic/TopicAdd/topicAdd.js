import React, {useEffect} from 'react';
import {useNavigate, useParams} from 'react-router-dom';

const TopicAdd = (props) => {
    const navigate = useNavigate();
    const {professorId: professorIdFromUrl, subjectId: subjectIdFromUrl} = useParams();

    const [formData, updateFormData] = React.useState({
        name: "",
        description: "",
        fromDate: "",
        toDate: "",
        groupCount: "",
        membersPerGroup: "",
        professorId: professorIdFromUrl || props.currentProfessorId || "",
        subjectId: subjectIdFromUrl || props.currentSubjectId || ""
    });

    useEffect(() => {
        if (professorIdFromUrl) {
            updateFormData((formData) => ({
                ...formData,
                professorId: professorIdFromUrl
            }));
        }
    }, [professorIdFromUrl]);

    useEffect(() => {
        if (subjectIdFromUrl) {
            updateFormData((formData) => ({
                ...formData,
                subjectId: subjectIdFromUrl
            }));
        }
    }, [subjectIdFromUrl]);

    const handleChange = (e) => {
        const value = e.target.name === 'professorId' || e.target.name === 'subjectId'
            ? e.target.value
            : e.target.value.trim();
        updateFormData({
            ...formData,
            [e.target.name]: value
        });
    };

    const onFormSubmit = (e) => {
        e.preventDefault();

        props.onAddTopic(
            formData.name,
            formData.description,
            formData.fromDate,
            formData.toDate,
            parseInt(formData.groupCount),
            parseInt(formData.membersPerGroup),
            formData.professorId,
            formData.subjectId
        );
        navigate(`/subject-allocations/professors/${formData.professorId}/subjects/${formData.subjectId}/topics`);
    };

    return (
        <div className="topic-add-wrapper d-flex justify-content-center align-items-center">
            <div className="topic-add-form p-4 rounded shadow align-items-center">
                <h2 className="mb-4 text-center text-white">Add New Topic</h2>
                <form onSubmit={onFormSubmit}>

                    <div className="form-group mb-3">
                        <label htmlFor="name">Topic Name</label>
                        <input
                            type="text"
                            className="form-control"
                            id="name"
                            name="name"
                            required
                            placeholder="Enter topic name"
                            onChange={handleChange}
                        />
                    </div>

                    <div className="form-group mb-3">
                        <label htmlFor="description">Description</label>
                        <textarea
                            className="form-control"
                            id="description"
                            name="description"
                            placeholder="Enter description"
                            onChange={handleChange}
                            rows={3}
                            required
                        />
                    </div>

                    <div className="form-group mb-3">
                        <label htmlFor="fromDate">From Date</label>
                        <input
                            type="date"
                            className="form-control"
                            id="fromDate"
                            name="fromDate"
                            required
                            onChange={handleChange}
                        />
                    </div>

                    <div className="form-group mb-3">
                        <label htmlFor="toDate">To Date</label>
                        <input
                            type="date"
                            className="form-control"
                            id="toDate"
                            name="toDate"
                            required
                            onChange={handleChange}
                        />
                    </div>

                    <div className="form-group mb-3">
                        <label htmlFor="groupCount">Group Count</label>
                        <input
                            type="number"
                            className="form-control"
                            id="groupCount"
                            name="groupCount"
                            placeholder="Enter number of groups"
                            required
                            onChange={handleChange}
                        />
                    </div>

                    <div className="form-group mb-3">
                        <label htmlFor="membersPerGroup">Members per Group</label>
                        <input
                            type="number"
                            className="form-control"
                            id="membersPerGroup"
                            name="membersPerGroup"
                            placeholder="Enter number of members"
                            required
                            onChange={handleChange}
                        />
                    </div>

                    <div className="form-group mb-3">
                        <label htmlFor="professorId">Professor ID</label>
                        <input
                            type="text"
                            className="form-control"
                            id="professorId"
                            name="professorId"
                            value={formData.professorId}
                            readOnly
                        />
                    </div>

                    <div className="form-group mb-4">
                        <label htmlFor="subjectId">Subject ID</label>
                        <input
                            type="text"
                            className="form-control"
                            id="subjectId"
                            name="subjectId"
                            value={formData.subjectId}
                            readOnly
                        />
                    </div>

                    <button id="submit" type="submit" className="btn btn-primary w-100">Submit</button>

                    <button className="btn text-white back-button" onClick={() => navigate(`/subject-allocations/professors/${formData.professorId}/subjects/${formData.subjectId}/topics`)}>
                        ⬅ Back to Topics!
                    </button>
                </form>
            </div>
        </div>
    );
};

export default TopicAdd;

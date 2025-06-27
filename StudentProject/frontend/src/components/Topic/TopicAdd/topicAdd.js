import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import api from "../../../custom-axios/axios";

const TopicAdd = (props) => {
    const navigate = useNavigate();
    const { professorId: professorIdFromUrl, subjectId: subjectIdFromUrl } = useParams();

    const [subjectName, setSubjectName] = useState("");

    const [formData, updateFormData] = useState({
        name: "",
        description: "",
        fromDate: "",
        toDate: "",
        groupCount: "",
        membersPerGroup: "",
        professorId: professorIdFromUrl || props.currentProfessorId || "",
        subjectId: subjectIdFromUrl || props.currentSubjectId || ""
    });

    // Fetch subject name
    useEffect(() => {
        if (formData.professorId && formData.subjectId) {
            api.get(`/subject-allocations/${formData.professorId}/subjects`)
                .then(res => {
                    const subjects = res.data || [];
                    const matched = subjects.find(s =>
                        s.abbreviation === formData.subjectId ||
                        s.subjectCode === formData.subjectId ||
                        s.semesterCode === formData.subjectId
                    );
                    if (matched) {
                        setSubjectName(matched.subjectName || matched.subject || "Unknown Subject");
                    }
                })
                .catch(() => setSubjectName("Unknown Subject"));
        }
    }, [formData.professorId, formData.subjectId]);

    const handleChange = (e) => {
        const value = e.target.value.trim();
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

                {/* Display professor and subject name */}

                <div className="form-group mb-4 text-white">
                    <label>Subject:</label>
                    <div><strong>{subjectName}</strong></div>
                </div>

                {/* Hidden inputs to submit professorId and subjectId */}
                <input type="hidden" name="professorId" value={formData.professorId} />
                <input type="hidden" name="subjectId" value={formData.subjectId} />

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
                            min="1"
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
                            min="1"
                        />
                    </div>

                    <button id="submit" type="submit" className="btn btn-primary w-100">
                        Submit
                    </button>

                    <button
                        type="button"
                        className="btn text-white back-button mt-2"
                        onClick={() =>
                            navigate(`/subject-allocations/professors/${formData.professorId}/subjects/${formData.subjectId}/topics`)
                        }
                    >
                        ⬅ Back to Topics!
                    </button>
                </form>
            </div>
        </div>
    );
};

export default TopicAdd;

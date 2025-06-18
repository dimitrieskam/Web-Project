import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from 'react-router-dom';

const TopicEdit = (props) => {
    const navigate = useNavigate();
    const {id: topicId, professorId: professorIdFromUrl, subjectId: subjectIdFromUrl} = useParams();

    const [formData, updateFormData] = useState({
        name: "",
        description: "",
        fromDate: "",
        toDate: "",
        groupCount: "",
        membersPerGroup: "",
        professorId: professorIdFromUrl || "",
        subjectId: subjectIdFromUrl || ""
    });

    const [submitting, setSubmitting] = useState(false);

    useEffect(() => {
        if (props.topics && topicId) {
            const topic = props.topics.find(t => t.id.toString() === topicId);
            if (topic) {
                updateFormData({
                    name: topic.name || "",
                    description: topic.description || "",
                    fromDate: topic.fromDate || "",
                    toDate: topic.toDate || "",
                    groupCount: topic.groupCount?.toString() || "",
                    membersPerGroup: topic.membersPerGroup?.toString() || "",
                    professorId: professorIdFromUrl || topic.professorId || "",
                    subjectId: subjectIdFromUrl || topic.subjectId || ""
                });
            }
        }
    }, [props.topics, topicId, professorIdFromUrl, subjectIdFromUrl]);

    const handleChange = (e) => {
        const {name, value} = e.target;
        updateFormData({
            ...formData,
            [name]: value
        });
    };

    const onFormSubmit = async (e) => {
        e.preventDefault();

        if (formData.toDate < formData.fromDate) {
            alert("The 'To Date' cannot be earlier than 'From Date'.");
            return;
        }

        const groupCount = parseInt(formData.groupCount, 10);
        const membersPerGroup = parseInt(formData.membersPerGroup, 10);

        if (isNaN(groupCount) || isNaN(membersPerGroup)) {
            alert("Group Count and Members per Group must be valid numbers.");
            return;
        }

        setSubmitting(true);
        try {
            await props.onEditTopic(
                topicId,
                formData.name.trim(),
                formData.description.trim(),
                formData.fromDate,
                formData.toDate,
                groupCount,
                membersPerGroup,
                formData.professorId,
                formData.subjectId
            );
            navigate(`/subject-allocations/professors/${formData.professorId}/subjects/${formData.subjectId}/topics`);
        } catch (error) {
            console.error("Error updating topic:", error);
            alert("Failed to update topic. Please try again.");
        } finally {
            setSubmitting(false);
        }
    };

    if (!props.topics) {
        return <p>Loading topics data...</p>;
    }

    return (
        <div className="topic-edit-wrapper d-flex justify-content-center align-items-center">
            <div className="topic-edit-form p-4 rounded shadow align-items-center">
                <h2 className="mb-4 text-center text-white">Edit Topic</h2>
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
                            value={formData.name}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="form-group mb-3">
                        <label htmlFor="description">Description</label>
                        <textarea
                            className="form-control"
                            id="description"
                            name="description"
                            rows={3}
                            required
                            placeholder="Enter description"
                            value={formData.description}
                            onChange={handleChange}
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
                            value={formData.fromDate}
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
                            value={formData.toDate}
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
                            required
                            placeholder="Enter number of groups"
                            value={formData.groupCount}
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
                            required
                            placeholder="Enter number of members"
                            value={formData.membersPerGroup}
                            onChange={handleChange}
                            min="1"
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

                    <button id="submit" type="submit" className="btn btn-primary w-100" disabled={submitting}>
                        {submitting ? "Saving..." : "Submit"}
                    </button>

                    <button
                        type="button"
                        className="btn text-white back-button mt-3"
                        onClick={() => navigate(`/subject-allocations/professors/${formData.professorId}/subjects/${formData.subjectId}/topics`)}
                    >
                        ⬅ Back to Topics!
                    </button>
                </form>
            </div>
        </div>
    );
};

export default TopicEdit;
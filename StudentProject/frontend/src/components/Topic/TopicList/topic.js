import React from 'react';
import ReactPaginate from 'react-paginate';
import { Link } from 'react-router-dom';
import TopicTerm from '../TopicTerm/topicTerm';
import TopicWrapper from "../TopicWrapper/topicWrapper";
import authService from "../../../repository/Authentication/auth_service";
import './topic.css';

class Topic extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            page: 0,
            size: 10,
            searchText: '',
            role: null
        };
    }

    componentDidMount() {
        const currentUser = authService.getCurrentUser();
        this.setState({ role: currentUser?.role || null });
        console.log("User role:", currentUser?.role);
    }

    handlePageClick = (data) => {
        this.setState({ page: data.selected });
    };

    handleSearchChange = (e) => {
        const newSearchText = e.target.value;
        this.setState({ searchText: newSearchText, page: 0 });
    };

    getTopicPage = (offset, nextPageOffset, filteredTopics) => {
        return filteredTopics
            .slice(offset, nextPageOffset)
            .map((term) => (
                <TopicTerm
                    key={term.id}
                    term={term}
                    onDelete={this.props.onDelete}
                    onEdit={this.props.onEdit}
                />
            ));
    };

    render() {
        const { size, page, searchText} = this.state;
        const topics = this.props.topics || [];
        const { professorId, subjectId } = this.props;
        const { userId, role } = this.props; // or from state if passed

        const filteredTopics = topics.filter(topic => {
            // Only show topics where topic.professorId matches logged-in professor's id
            if (role === "ROLE_PROFESSOR" && userId) {
                return topic.professorId === userId &&
                    (topic.name.toLowerCase().includes(searchText.toLowerCase()) ||
                        (topic.description && topic.description.toLowerCase().includes(searchText.toLowerCase())));
            }
            // For other roles, show all or apply other logic
            return topic.name.toLowerCase().includes(searchText.toLowerCase()) ||
                (topic.description && topic.description.toLowerCase().includes(searchText.toLowerCase()));
        });

        const offset = size * page;
        const nextPageOffset = offset + size;
        const pageCount = Math.ceil(filteredTopics.length / size);
        const topicsPage = this.getTopicPage(offset, nextPageOffset, filteredTopics);

        return (
            <div className="container topic-container mt-5">
                <div className="topics-title mb-4 text-center">
                    <h2>Topics</h2>
                </div>

                <div className="mb-4">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Search topics by name or description..."
                        value={searchText}
                        onChange={this.handleSearchChange}
                    />
                </div>

                <div className="table-responsive">
                    <table className="table table-striped table-dark">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>From Date</th>
                            <th>To Date</th>
                            <th>Group Count</th>
                            <th>Members per Group</th>
                            <th>Professor</th>
                            <th>Subject</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>{topicsPage}</tbody>
                    </table>
                </div>

                {role === "ROLE_PROFESSOR" && (
                    <div className="mb-3 text-center">
                        <Link
                            className="btn btn-dark"
                            to={`/subject-allocations/professors/${professorId}/subjects/${subjectId}/topics/add-topic`}
                        >
                            Add new Topic
                        </Link>
                    </div>
                )}

                <div className="d-flex justify-content-center">
                    <ReactPaginate
                        previousLabel={"←"}
                        nextLabel={"→"}
                        breakLabel={<span className="mx-2">...</span>}
                        pageCount={pageCount}
                        onPageChange={this.handlePageClick}
                        containerClassName={"pagination"}
                        pageClassName={"page-item"}
                        pageLinkClassName={"page-link"}
                        previousClassName={"page-item"}
                        previousLinkClassName={"page-link"}
                        nextClassName={"page-item"}
                        nextLinkClassName={"page-link"}
                        breakClassName={"page-item"}
                        breakLinkClassName={"page-link"}
                        activeClassName={"active"}
                    />
                </div>
            </div>
        );
    }
}

export default Topic;

import React from 'react';
import ReactPaginate from 'react-paginate';
import SubjectTerm from '../SubjectTerm/subjectTerm';

class Subject extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            page: 0,
            size: 12,
            name: "",
            semesterType: ""
        };
    }

    handlePageClick = (data) => {
        this.setState({ page: data.selected });
    };

    getSubjectPage = (offset, nextPageOffset) => {
        return this.props.subjects
            .slice(offset, nextPageOffset)
            .map((term, index) => <SubjectTerm key={term.id || index} term={term} />);
    };
    handleInputChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    };

    handleSearchSubmit = (e) => {
        e.preventDefault();
        this.props.onSearchSubjects(this.state.name, this.state.semesterType);
        this.setState({ page: 0 });
    };

    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.subjects.length / this.state.size);
        const subjects = this.getSubjectPage(offset, nextPageOffset);

        return (
            <div className="container mt-5">
                <form className="mb-4" onSubmit={this.handleSearchSubmit}>
                    <div className="row g-2 align-items-end">
                        <div className="col-md-5">
                            <label className="form-label">Subject Name</label>
                            <input
                                type="text"
                                className="form-control"
                                name="name"
                                value={this.state.name}
                                onChange={this.handleInputChange}
                            />
                        </div>

                        <div className="col-md-4">
                            <label className="form-label">Semester Type</label>
                            <select
                                className="form-select"
                                name="semesterType"
                                value={this.state.semesterType}
                                onChange={this.handleInputChange}
                            >
                                <option value="">All</option>
                                <option value="SUMMER">SUMMER</option>
                                <option value="WINTER">WINTER</option>
                            </select>
                        </div>

                        <div className="col-md-3">
                            <button type="submit" className="btn btn-primary w-100">
                                Search
                            </button>
                        </div>
                    </div>
                </form>

                <div className="row">
                    {subjects}
                </div>

                <div className="d-flex justify-content-center mt-4">
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

export default Subject;

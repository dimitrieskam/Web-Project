import React from 'react';
import ReactPaginate from 'react-paginate';
import SubjectTerm from '../SubjectTerm/subjectTerm';

class Subject extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            page: 0,
            size: 12,
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

    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.subjects.length / this.state.size);
        const subjects = this.getSubjectPage(offset, nextPageOffset);

        return (
            <div className="container mt-5">
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

import React from 'react';
import ReactPaginate from 'react-paginate';
import ProfessorTerm from '../ProfessorTerm/professorTerm';

class Professor extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            page: 0,
            size: 12,
            searchText: ''
        };
    }
    handlePageClick = (data) => {
        this.setState({ page: data.selected });
    };

    getProfessorPage = (offset, nextPageOffset) => {
        return this.props.professors
            .map((term, index) => (
                <ProfessorTerm term={term} key={index} />
            ))
            .filter((_, index) => index >= offset && index < nextPageOffset);
    };

    handleSearchChange = (e) => {
        const newSearchText = e.target.value;
        this.setState({ searchText: newSearchText, page: 0 }, () => {
            if (this.props.onSearchProfessors) {
                this.props.onSearchProfessors(this.state.searchText);
            }
        });
    };

    handleSearchSubmit = () => {
        if (this.props.onSearchProfessors) {
            this.props.onSearchProfessors(this.state.searchText);
        }
    };
    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.professors.length / this.state.size);
        const professors = this.getProfessorPage(offset, nextPageOffset);

        return (
            <div className="container mt-5">
                <div className="mb-4">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Search professors by name..."
                        value={this.state.searchText}
                        onChange={this.handleSearchChange}
                    />
                </div>

                <div className="row">
                    {professors}
                </div>

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

export default Professor;

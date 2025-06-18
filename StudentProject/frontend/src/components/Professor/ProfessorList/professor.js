import React from 'react';
import ReactPaginate from 'react-paginate';
import ProfessorTerm from '../ProfessorTerm/professorTerm';
import './professor.css';

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
        this.setState({page: data.selected});
    };

    getProfessorPage = (offset, nextPageOffset, filteredProfessors) => {
        const count = filteredProfessors.length;

        let gridClass = "col-sm-6 col-md-4 col-lg-3 mb-4 professor-card";

        if (count === 1) {
            gridClass = "col-12 mb-4 professor-card";
        } else if (count === 2) {
            gridClass = "col-md-6 mb-4 professor-card";
        } else if (count === 3) {
            gridClass = "col-md-4 mb-4 professor-card";
        }

        return filteredProfessors
            .slice(offset, nextPageOffset)
            .map((term, index) => (
                <div className={gridClass} key={index}>
                    <ProfessorTerm term={term}/>
                </div>
            ));
    };

    handleSearchChange = (e) => {
        const newSearchText = e.target.value;
        this.setState({searchText: newSearchText, page: 0}, () => {
            if (this.props.onSearchProfessors) {
                this.props.onSearchProfessors(this.state.searchText);
            }
        });
    };

    render() {
        const { size, page, searchText } = this.state;

        const filteredProfessors = this.props.professors.filter((prof) =>
            prof.name.toLowerCase().includes(searchText.toLowerCase())
        );

        const offset = size * page;
        const nextPageOffset = offset + size;
        const pageCount = Math.ceil(filteredProfessors.length / size);

        const professors = this.getProfessorPage(offset, nextPageOffset, filteredProfessors);

        return (
            <div className="container professor-container mt-5">
                <div className="professors-title mb-4">
                    <h2>Professors</h2>
                </div>

                <div className="mb-4">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Search professors by name..."
                        value={searchText}
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

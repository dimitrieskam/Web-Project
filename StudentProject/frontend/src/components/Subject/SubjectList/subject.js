import React from 'react';
import ReactPaginate from 'react-paginate'
import {Link} from 'react-router-dom';
import SubjectTerm from '../SubjectTerm/subjectTerm';

class Subject extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            page: 0,
            size: 10
        }
    }

    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.subjects.length / this.state.size);
        const subjects = this.getSubjectPage(offset, nextPageOffset);
        console.log(subjects, pageCount)

        return (
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"table-responsive"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Code</th>
                                <th scope={"col"}>Students</th> 
                                <th scope={"col"}>Professors</th>
                            </tr>
                            </thead>
                            <tbody>
                            {subjects}
                            </tbody>
                        </table>
                    </div>
                    <div className="col mb-3">
                        <div className="row">
                            <div className="col-sm-12 col-md-12">
                                <Link className={"btn btn-block btn-dark"} to={"/subjects/add-subject"}>Add new Subject</Link>
                            </div>
                        </div>
                    </div>
                </div>
                <ReactPaginate previousLabel={"back"}
                               nextLabel={"next"}
                               breakLabel={<a href="/#">...</a>}
                               breakClassName={"break-me"}
                               pageClassName={"ml-1"}
                               pageCount={pageCount}
                               marginPagesDisplayed={2}
                               pageRangeDisplayed={5}
                               onPageChange={this.handlePageClick}
                               containerClassName={"pagination m-4 justify-content-center"}
                               activeClassName={"active"}/>
            </div>
        )
    }

    handlePageClick = (data) => {
        let selected = data.selected;
        console.log(selected)
        this.setState({
            page: selected
        })
    }

    getSubjectPage = (offset, nextPageOffset) => {
        console.log(offset, nextPageOffset)
        return this.props.subjects.map((term, index) => {
            return (
                <SubjectTerm term={term} onDelete={this.props.onDelete} onEdit={this.props.onEdit}/>
            );
        }).filter((subject, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }
}

export default Subject;
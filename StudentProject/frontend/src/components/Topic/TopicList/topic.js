import React from 'react';
import ReactPaginate from 'react-paginate'
import {Link} from 'react-router-dom';
import TopicTerm from '../TopicTerm/topicTerm';

class Topic extends React.Component {

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
        const pageCount = Math.ceil(this.props.topics.length / this.state.size);
        const topics = this.getTopicPage(offset, nextPageOffset);
        console.log(topics, pageCount)

        return (
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"table-responsive"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>From Date</th>
                                <th scope={"col"}>To Date</th>
                                <th scope={"col"}>Group Count</th>
                                <th scope={"col"}>Members per Group</th>
                                <th scope={"col"}>Subject</th>
                            </tr>
                            </thead>
                            <tbody>
                            {topics}
                            </tbody>
                        </table>
                    </div>
                    <div className="col mb-3">
                        <div className="row">
                            <div className="col-sm-12 col-md-12">
                                <Link className={"btn btn-block btn-dark"} to={"/topics/add-topic"}>Add new Topic</Link>
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

    getTopicPage = (offset, nextPageOffset) => {
        console.log(offset, nextPageOffset)
        return this.props.topics.map((term, index) => {
            return (
                <TopicTerm term={term} onDelete={this.props.onDelete} onEdit={this.props.onEdit}/>
            );
        }).filter((topic, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }
}

export default Topic;
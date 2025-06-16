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
        const topics = this.props.topics || [];  // default to empty array
        const pageCount = Math.ceil(topics.length / this.state.size);
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const topicsPage = this.getTopicPage(offset, nextPageOffset, topics);


        return (
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"table-responsive"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Description</th>
                                <th scope={"col"}>From Date</th>
                                <th scope={"col"}>To Date</th>
                                <th scope={"col"}>Group Count</th>
                                <th scope={"col"}>Members per Group</th>
                                <th scope={"col"}>Professor</th>
                                <th scope={"col"}>Subject</th>
                                <th scope={"col"}>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            {topicsPage}
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

    getTopicPage = (offset, nextPageOffset, topics) => {
    return topics
      .map((term, index) => (
          <TopicTerm key={term.id} term={term} onDelete={this.props.onDelete} onEdit={this.props.onEdit} />
      ))
      .filter((topic, index) => index >= offset && index < nextPageOffset);
}

}

export default Topic;
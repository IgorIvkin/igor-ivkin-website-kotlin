'use strict';

class Pagination extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            totalFound: props.totalFound,
            totalPages: props.totalPages,
            currentPage: props.currentPage,
            basicUrl: props.basicUrl || "/articles/pages/"
        }
    }

    pageIndexIsCurrentPage(index) {
        return parseInt(index + 1) === parseInt(this.state.currentPage)
    }

    renderPageLink(index) {
        return (this.state.basicUrl + index);
    }

    renderCurrentPageItemWithLink(index) {
        let pageNumber = index + 1;
        return (
            pageNumber - this.state.currentPage <= 3 &&
            pageNumber - this.state.currentPage >= -3 &&
            <div className={"item"} key={pageNumber}>
                <a href={this.renderPageLink(pageNumber)}>{pageNumber}</a>
            </div>
        );
    }

    renderFirstPageItem() {
        return (
            this.state.currentPage > 4 &&
            <div className={"item"} key={1}>
                <a href={this.renderPageLink(1)}>&laquo;</a>
            </div>
        );
    }

    renderLastPageItem() {
        return (
            this.state.totalPages - this.state.currentPage > 4 &&
            <div className={"item"} key={this.state.totalPages}>
                <a href={this.renderPageLink(this.state.totalPages)}>&raquo;</a>
            </div>
        );
    }

    renderCurrentPageItemWithoutLink(index) {
        let pageNumber = index + 1;
        return (
            <div className={"item"} key={pageNumber}>{pageNumber}</div>
        );
    }

    render() {
        console.log("Rendering Pagination");
        let pagination = this;
        return (
            pagination.state.totalPages > 1 &&
            <div className={"pagination"}>
                {pagination.renderFirstPageItem()}
                {Array.from(Array(pagination.state.totalPages), (e, i) => {
                    return (
                        pagination.pageIndexIsCurrentPage(i) &&
                        pagination.renderCurrentPageItemWithoutLink(i) ||
                        pagination.renderCurrentPageItemWithLink(i)
                    );
                })}
                {pagination.renderLastPageItem()}
            </div>
        );
    }
}

document.addEventListener("DOMContentLoaded", function (event) {
    const paginationBlock = document.querySelector('.pagination-block');
    if (paginationBlock) {
        ReactDOM.render(
            React.createElement(
                Pagination,
                {
                    totalFound: window.totalFound,
                    totalPages: window.totalPages,
                    currentPage: window.currentPage,
                    basicUrl: window.basicPaginationUrl
                },
                null
            ), paginationBlock
        );
    }
});
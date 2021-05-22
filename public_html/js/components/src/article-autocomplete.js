'use strict';

class ArticleAutocomplete extends React.Component {
    constructor(props) {
        super(props);
        this.typingTimeout = null;
        this.autocompleteInput = React.createRef();
        this.state = {
            articles: this.props.articles || [],
            foundArticles: []
        }
    }

    async searchArticlesByApi(query) {
        let response = await fetch('/api/articles/?title=' + encodeURIComponent(query), {
            method: 'GET'
        });
        if (response.ok) {
            let articlesFromApi = await response.json();
            this.setState({
                foundArticles: articlesFromApi
            });
        }
        return response;
    }

    doLookupSearch(event) {
        let component = this;
        let query = event.target.value;
        if (query && query.length >= 3) {
            if (this.typingTimeout) {
                clearTimeout(this.typingTimeout);
            }
            this.typingTimeout = setTimeout(() => {
                component.setState({
                    foundArticles: []
                });
                let response = this.searchArticlesByApi(query);
            }, 500);
        }
    }

    addArticleToCourse(event) {
        this.setState({
            foundArticles: []
        });
        let articleId = event.target.getAttribute("data-article-id");
        let articleTitle = event.target.getAttribute("data-article-title");
        let currentSetArticles = this.state.articles;
        if (!currentSetArticles.map((article) => article.id).includes(articleId)) {
            currentSetArticles.push({id: articleId, title: articleTitle});
            this.setState({
                articles: currentSetArticles
            });
            this.autocompleteInput.current.value = "";
        }
    }

    render() {
        console.log("Rendering ArticleAutocomplete");
        return (
            <div>
                <label htmlFor="articles">Статьи (начните набирать название статьи, чтобы добавить её в
                    курс)</label><br/>
                <input ref={this.autocompleteInput}
                       onChange={(event) => this.doLookupSearch(event)}
                       type="text"
                       autoComplete={"off"}
                       id="articles"
                       name="articles"/>
                {this.state.foundArticles.length > 0 &&
                <div className={"autocomplete"}>
                    {this.state.foundArticles.map(article => {
                        return (
                            <div key={article.id} className={"item"}>
                                <a
                                    href={"#!"}
                                    data-article-id={article.id}
                                    data-article-title={article.title}
                                    onClick={(event) => this.addArticleToCourse(event)}>{article.title}</a>
                            </div>
                        );
                    })}
                </div>
                }
                {this.state.articles.length > 0 &&
                <div className={"course-articles"}>
                    {this.state.articles.map(article => {
                        return (
                            <div key={article.id} data-article-id={article.id} className={"item"}>
                                <span className={"article-title"}>{article.title}</span>
                            </div>
                        );
                    })}
                </div>
                }
            </div>
        );
    }
}

document.addEventListener("DOMContentLoaded", function (event) {
    const domContainer = document.querySelector('#article-initialization-block');
    if (domContainer) {
        ReactDOM.render(React.createElement(ArticleAutocomplete, {articles: window.courseArticles}, null), domContainer);
    }
});

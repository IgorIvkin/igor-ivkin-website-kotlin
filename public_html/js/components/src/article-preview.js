'use strict';

class ArticlePreview extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            onceRendered: false,
            htmlContent: ""
        }
    }

    async makeApiCallToRerender(content) {
        let response = await fetch('/api/markdown/', {
            method: 'POST',
            mode: 'cors',
            cache: 'no-cache',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(content)
        });
        if (response.ok) {
            let content = await response.json();
            this.setState({
                onceRendered: true,
                htmlContent: content.content
            });
        }
        return response;
    }

    getArticleTitle() {
        let titleInput = document.getElementById("title");
        if (titleInput) {
            return titleInput.value;
        } else {
            return "";
        }
    }

    reRenderHtml() {
        let contentInput = document.getElementById("content");
        if (contentInput) {
            let response = this.makeApiCallToRerender({
                "content": contentInput.value
            })
        } else {
            return "";
        }
    }

    render() {
        console.log("Rendering ArticlePreview");
        if(this.state.onceRendered) {
            return (
                <div className={"article-preview"}>
                    <h1>{this.getArticleTitle()}</h1>
                    <div dangerouslySetInnerHTML={{__html: this.state.htmlContent}} />
                </div>
            );
        } else {
            return (<div>&nbsp;</div>);
        }
    }
}

let articlePreview = null;
document.addEventListener("DOMContentLoaded", function (event) {
    // Initialize preview component
    const domContainer = document.querySelector('#article-preview-block');
    if (domContainer) {
        articlePreview = ReactDOM.render(React.createElement(ArticlePreview, {}, null), domContainer);
    }

    // Bind an event to handle the button click 'Preview'
    const buttonPreview = document.getElementById("preview-button");
    if (buttonPreview) {
        buttonPreview.addEventListener("click", function (event) {
            if (articlePreview) {
                articlePreview.reRenderHtml();
            }
        });
    }
});
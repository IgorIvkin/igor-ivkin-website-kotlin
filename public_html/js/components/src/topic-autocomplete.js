'use strict';

class TopicAutocomplete extends React.Component {
    constructor(props) {
        super(props);
        this.typingTimeout = null;
        this.autocompleteInput = React.createRef();
        this.state = {
            setTopics: this.props.setTopics || [],
            foundTopics: []
        }
    }

    async makeApiRequest(query) {
        let response = await fetch('/api/topics/' + query, {
            method: 'GET'
        });
        if (response.ok) {
            let topicsFromApi = await response.json();
            this.setState({
                foundTopics: topicsFromApi
            });
        }
        return response;
    }

    doLookupSearch(event) {
        let component = this;
        let query = event.target.value;
        if (this.typingTimeout) {
            clearTimeout(this.typingTimeout);
        }
        this.typingTimeout = setTimeout(() => {
            component.setState({
                foundTopics: []
            });
            let response = this.makeApiRequest(query);
        }, 500);
    }

    addSetTopic(event) {
        this.setState({
            foundTopics: []
        });
        let topicId = event.target.getAttribute("data-topic-id");
        let topicTitle = event.target.getAttribute("data-topic-title");
        let currentSetTopics = this.state.setTopics;
        if (!currentSetTopics.map((topic) => topic.id).includes(topicId)) {
            currentSetTopics.push({id: topicId, title: topicTitle});
            this.setState({
                setTopics: currentSetTopics
            });
            this.autocompleteInput.current.value = "";
        }
    }

    deleteSetTopic(event) {
        let topicId = event.target.getAttribute("data-topic-id");
        let currentSetTopics = this.state.setTopics;
        this.setState({
            setTopics: currentSetTopics.filter(topic => topic.id.toString() !== topicId.toString())
        });
    }

    render() {
        console.log("Rendering TopicAutocomplete");
        return (
            <div>
                <label htmlFor="topics">Темы (обязательно выбрать хотя бы одну)</label><br/>
                <input
                    ref={this.autocompleteInput}
                    onChange={(event) => this.doLookupSearch(event)}
                    type="text"
                    id="topics"
                    name="topics"/>
                {this.state.foundTopics.length > 0 &&
                    <div className={"autocomplete"}>
                        {this.state.foundTopics.map(topic => {
                            return (
                                <div key={topic.id} className={"item"}>
                                    <a
                                        href={"#!"}
                                        data-topic-id={topic.id}
                                        data-topic-title={topic.title}
                                        onClick={(event) => this.addSetTopic(event)}>{topic.title}</a>
                                </div>
                            );
                        })}
                    </div>
                }
                {this.state.setTopics.length > 0 &&
                    <div className={"set-topics"}>
                        {this.state.setTopics.map(topic => {
                            return (
                                <div key={topic.id} data-topic-id={topic.id} className={"item"}>
                                    <span className={"set-topic-title"}>{topic.title}</span>
                                    <span
                                        onClick={(event) => this.deleteSetTopic(event)}
                                        data-topic-id={topic.id}
                                        className={"set-topic-delete"}>✕</span>
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
    const domContainer = document.querySelector('#topic-initialization-block');
    if (domContainer) {
        ReactDOM.render(React.createElement(TopicAutocomplete, {setTopics: window.setTopics}, null), domContainer);
    }
});

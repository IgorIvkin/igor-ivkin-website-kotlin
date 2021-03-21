'use strict';

async function makeApiRequest(requestPayload) {
    let response = await fetch('/admin/articles/do-add/', {
        method: 'POST',
        headers: {
            "content-type": "application/json"
        },
        body: JSON.stringify(requestPayload),
    });
    if (response.ok) {
        let topicsFromApi = await response.json();
    }
    return response;
}

function sendArticleForm(form) {
    let requestPayload = {};
    requestPayload["author"] = {
        id: document.getElementById("author.id").value
    };
    requestPayload["title"] = document.getElementById("title").value;
    requestPayload["content"] = document.getElementById("content").value;
    let topics = [];
    let setTopicsElements = document.querySelectorAll(".set-topics .item");
    setTopicsElements.forEach((topicElement, index, array) => {
        topics.push({
            id: topicElement.getAttribute("data-topic-id")
        });
    });
    requestPayload["topics"] = topics;
    return makeApiRequest(requestPayload);
}

document.addEventListener("DOMContentLoaded", function (event) {
    let articleForm = document.getElementById('article-edit-form');
    if (articleForm) {
        articleForm.addEventListener("submit", function (event) {
            event.preventDefault();
            event.stopPropagation();
            return sendArticleForm(this);
        })
    }
});

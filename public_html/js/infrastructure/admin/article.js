'use strict';

async function makeApiRequest(requestPayload, action) {
    let response = await fetch(action, {
        method: 'POST',
        headers: {
            "content-type": "application/json"
        },
        body: JSON.stringify(requestPayload),
    });
    if (response.ok) {
        let topicsFromApi = await response.json();
        if (topicsFromApi.id) {
            location.href = "/admin/articles/edit/" + topicsFromApi.id
        }
    }
    return response;
}

function sendArticleForm(form, action) {
    let requestPayload = {};
    let idInput = document.getElementById("id");
    if (idInput) {
        requestPayload["id"] = idInput.value;
    }
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
    return makeApiRequest(requestPayload, action);
}

document.addEventListener("DOMContentLoaded", function (event) {
    let articleForm = document.getElementById('article-edit-form');
    if (articleForm) {
        let formAction = articleForm.getAttribute("action");
        articleForm.addEventListener("submit", function (event) {
            event.preventDefault();
            event.stopPropagation();
            return sendArticleForm(this, formAction);
        })
    }
});

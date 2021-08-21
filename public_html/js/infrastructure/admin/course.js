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
        let coursesFromApi = await response.json();
        if (coursesFromApi.id) {
            location.href = "/admin/courses/edit/" + coursesFromApi.id
        }
    }
    return response;
}

function sendCourseForm(form, action) {
    let requestPayload = {};
    let idInput = document.getElementById("id");
    if (idInput) {
        requestPayload["id"] = idInput.value;
    }
    requestPayload["title"] = document.getElementById("title").value;
    requestPayload["description"] = document.getElementById("description").value;
    let articles = [];
    let courseArticlesElement = document.querySelectorAll(".course-articles .item");
    courseArticlesElement.forEach((articleElement, index, array) => {
        articles.push({
            articleId: articleElement.getAttribute("data-article-id"),
            courseId: idInput.value,
            order: index + 1
        });
    });
    requestPayload["articles"] = articles;
    return makeApiRequest(requestPayload, action);
}

document.addEventListener("DOMContentLoaded", function (event) {
    let courseForm = document.getElementById('course-edit-form');
    if (courseForm) {
        let formAction = courseForm.getAttribute("action");
        courseForm.addEventListener("submit", function (event) {
            event.preventDefault();
            event.stopPropagation();
            return sendCourseForm(this, formAction);
        })
    }
});

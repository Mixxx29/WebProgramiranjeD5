
function init() {
    // Get placeholder element
    const placeholder = document.getElementById("placeholder");

    // Set initial content
    let html = "<h1>Posts</h1>";

    // Get all posts
    fetch("http://localhost:8080/api/posts").then(result => result.json().then(posts => {
        posts.forEach(post => {
            let title = post.title;
            let content = (post.content.length > 500) ? post.content.substring(0, 500).concat("...") : post.content;

            html +=
                "   <div class=\"post\">\n" +
                "        <h2 class=\"post-title\">" + title + "</h2>\n" +
                "        <p class=\"post-content\">" + content + "</p>\n" +
                "        <p class='read-more' onclick='openPost(" + post.id + ")'>Read more...</p>\n" +
                "   </div>";
        });

        html += "<button type=\"button\" onclick=\"newPost()\" class=\"btn btn-primary\">New Post</button>";

        placeholder.innerHTML = html;
    })).catch(err => {
        console.log(err.message);
    })
}

function newPost() {
    const placeholder = document.getElementById("placeholder");

    placeholder.innerHTML =
        "        <h1>New Post</h1>" +
        "        <div class=\"mb-3\">\n" +
        "            <label for=\"authorInput\" class=\"form-label\">Author:</label>\n" +
        "            <input type=\"text\" class=\"form-control\" id=\"authorInput\" placeholder=\"Author\">\n" +
        "        </div>\n" +
        "        <div class=\"mb-3\">\n" +
        "            <label for=\"titleInput\" class=\"form-label\">Title:</label>\n" +
        "            <input type=\"text\" class=\"form-control\" id=\"titleInput\" placeholder=\"Title\">\n" +
        "        </div>\n" +
        "        <div class=\"mb-3\">\n" +
        "            <label for=\"contentInput\" class=\"form-label\">Content:</label>\n" +
        "            <textarea class=\"form-control\" id=\"contentInput\" rows=\"10\"></textarea>\n" +
        "        </div>" +
"                <button type=\"button\" onclick=\"publishPost()\" class=\"btn btn-primary\">Publish Post</button>";
}

function publishPost() {
    const authorInput = document.getElementById("authorInput");
    const titleInput = document.getElementById("titleInput");
    const contentInput = document.getElementById("contentInput");

    fetch("http://localhost:8080/api/posts", {
        method: "POST",
        headers: {
            "Content-Type" : "application/json"
        },
        body: JSON.stringify({
            author: authorInput.value,
            title: titleInput.value,
            content: contentInput.value
        })
    }).then(result => result.json().then(post => {
        window.location.reload();
    })).catch(err => {
        console.log(err.message);
    })
}

function openPost(postID) {
    const placeholder = document.getElementById("placeholder");

    fetch(`http://localhost:8080/api/posts/${postID}`).then(result => result.json().then(post => {
        let html =
            "    <h1>" + post.title + "</h1>" +
            "    <p id=\"author\"> Author: " + post.author + "</p>\n" +
            "    <p id=\"content\">" + post.content + "</p>" +
            "    <h2>Comments</h2>";

        fetch(`http://localhost:8080/api/comments/postID/${post.id}`).then(result => result.json().then(comments => {
            comments.forEach(comment => {
                let name = comment.name;
                let content = (comment.content.length > 500) ? comment.content.substring(0, 500).concat("...") : comment.content;

                html +=
                    "   <div class=\"comment\">\n" +
                    "        <h2 class=\"comment-name\">" + name + "</h2>\n" +
                    "        <p class=\"comment-content\">" + content + "</p>\n" +
                    "   </div>";
            });

            html +=
                "        <h3>New Comment</h3>" +
                "        <div class=\"mb-3\">\n" +
                "            <label for=\"nameInput\" class=\"form-label\">Name:</label>\n" +
                "            <input type=\"text\" class=\"form-control\" id=\"nameInput\" placeholder=\"Name\">\n" +
                "        </div>\n" +
                "        <div class=\"mb-3\">\n" +
                "            <label for=\"contentInput\" class=\"form-label\">Content:</label>\n" +
                "            <textarea class=\"form-control\" id=\"contentInput\" rows=\"5\"></textarea>\n" +
                "        </div>" +
                "        <button type=\"button\" onclick=\"comment(" + postID + ")\" class=\"btn btn-primary\">Comment</button>"

            placeholder.innerHTML = html;
        })).catch(err => {
            console.log(err.message);
        })
    })).catch(err => {
        console.log(err.message);
    });
}

function comment(postID) {
    const nameInput = document.getElementById("nameInput");
    const contentInput = document.getElementById("contentInput");

    if (nameInput.value.trim() === "" || contentInput.value.trim() === "") return;

    fetch("http://localhost:8080/api/comments", {
        method: "POST",
        headers: {
            "Content-Type" : "application/json"
        },
        body: JSON.stringify({
            postID: postID,
            name: nameInput.value,
            content: contentInput.value
        })
    }).then(result => result.json().then(comment => {
        openPost(postID)
    })).catch(err => {
        console.log(err.message);
    })
}
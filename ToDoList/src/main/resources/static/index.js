'use strict'

    let id = document.querySelector("#listid");
    let nameInput = document.querySelector("#listname");


    console.log(id);

    function getList() {
        let output = document.querySelector("#result");
        fetch("http://localhost:8080/todolist/", {
        method: 'get',    
        })
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json()
            .then(data => console.info(data))
        }).catch((err)=> console.error(`${err}`));
    }

    function postList() {
        fetch("http://localhost:8080/todolist", {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            "name" : nameInput.value
        })   
        })
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json()
            .then(data => console.info(data))
        }).catch((err)=> console.error(`${err}`));
    }

    function putList() {
        fetch("http://localhost:8080/todolist/" + id.value, {
        method: 'put',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            "name" : nameInput.value
        })  
        })
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json()
            .then(data => console.info(data))
        }).catch((err)=> console.error(`${err}`));
    }

    function deleteList() {
        fetch("http://localhost:8080/todolist/" + id.value, {
        method: 'delete',    
        })
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json()
            .then(data => console.info(data))
        }).catch((err)=> console.error(`${err}`));
    }


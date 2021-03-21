'use strict'

    let tdlId = document.querySelector("#listid");
    let tdlNameInput = document.querySelector("#listname");

    let taskId = document.querySelector("#taskid");
    let taskNameInput = document.querySelector("#taskname");

    let output = document.querySelector("#result");

    function getList() {
        fetch("http://localhost:8080/todolist/" + tdlId.value, {
        method: 'get',    
        })
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json()
            .then(data => output.value = JSON.stringify(data))
        }).catch((err)=> console.error(`${err}`));
    }

    function getListByName() {
        fetch("http://localhost:8080/todolist/name/" + tdlNameInput.value, {
        method: 'get',    
        })
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json()
            .then(data => output.value = JSON.stringify(data))
        }).catch((err)=> console.error(`${err}`));
    }

    function postList() {
        fetch("http://localhost:8080/todolist", {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            "name" : tdlNameInput.value
        })   
        })
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json()
            .then(data => output.value = JSON.stringify(data))
        }).catch((err)=> console.error(`${err}`));
    }

    function putList() {
        fetch("http://localhost:8080/todolist/" + tdlId.value, {
        method: 'put',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            "name" : tdlNameInput.value
        })  
        })
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json()
            .then(data => output.value = JSON.stringify(data))
        }).catch((err)=> console.error(`${err}`));
    }

    function deleteList() {
        fetch("http://localhost:8080/todolist/" + tdlId.value, {
        method: 'delete',    
        })
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json()
            .then(data => output.value = JSON.stringify(data))
        }).catch((err)=> console.error(`${err}`));
    }

    // ---------------TASKS---------------

    function getTask() {
        let output = document.querySelector("#result");
        fetch("http://localhost:8080/task/" + taskId.value, {
        method: 'get',    
        })
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json()
            .then(data => output.value = JSON.stringify(data))
        }).catch((err)=> console.error(`${err}`));
    }

    function getTaskByName() {
        let output = document.querySelector("#result");
        fetch("http://localhost:8080/task/name/" + taskNameInput.value, {
        method: 'get',    
        })
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json()
            .then(data => output.value = JSON.stringify(data))
        }).catch((err)=> console.error(`${err}`));
    }

    function postTask() {
        fetch("http://localhost:8080/task", {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            "name": taskNameInput.value,
            "toDoList": {
              "id": tdlId.value
            }
        })   
        })
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json()
            .then(data => output.value = JSON.stringify(data))
        }).catch((err)=> console.error(`${err}`));
    }

    function putTask() {
        fetch("http://localhost:8080/task/" + taskId.value, {
        method: 'put',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            "name": taskNameInput.value,
            "toDoList": {
              "id": tdlId.value
            }
        })  
        })
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json()
            .then(data => output.value = JSON.stringify(data))
        }).catch((err)=> console.error(`${err}`));
    }

    function deleteTask() {
        fetch("http://localhost:8080/task/" + taskId.value, {
        method: 'delete',    
        })
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json()
            .then(data => output.value = JSON.stringify(data))
        }).catch((err)=> console.error(`${err}`));
    }


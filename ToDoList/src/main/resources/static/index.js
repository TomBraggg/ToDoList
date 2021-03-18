'use strict'

    function get() {
        fetch("http://localhost:8080/todolist", {
        method: 'get',    
        }) // 1  
        .then((response) => {
            if (response.status !== 200) {  //  2
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json() // 3
            .then(data => console.info(data)) // 4
        }).catch((err)=> console.error(`${err}`)); // 5
    }

traerDatos()


function traerDatos(){
    console.log("dentro del boton funcion 2");

    const xhttp = new XMLHttpRequest();

    xhttp.open('GET','listar',true);

    xhttp.send();

    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status==200){
            let datos  = JSON.parse(this.responseText);
            let res = document.querySelector('#res');
            res.innerHTML=''

            console.log(datos);
            for (let item of datos){
                res.innerHTML += `
                <tr> 
                    <td> ${item.id}</td>
                    <td> ${item.costoHora}</td>
                    <td> ${item.costoMatricula}</td>
                    <td> ${item.totalHoras}</td>
                    <td> ${item.estado}</td>


                </tr>
                `
            }
        }

    }

}








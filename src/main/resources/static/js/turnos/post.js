window.addEventListener('load', function () {

     //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
     //los datos que el usuario cargará del nuevo turno
    const formulario = document.querySelector('#add_new_turno');

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {

        //creamos un JSON que tendrá los datos del nuevo paciente
        const formData = {
            id:document.querySelector('#id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            id_odontologo: document.querySelector('#id_odontologo').value,

        };

        //invocamos utilizando la función fetch la API turnos con el método POST
        //que guardará al estudiante que enviaremos en formato JSON
        const url = 'http://localhost:8081/turnos/registrar';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
               //Si no hay ningun error se muestra un mensaje diciendo que el turno
               //se agrego bien
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Turno agregado </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 //se dejan todos los campos vacíos por si se quiere ingresar otro turno
                 resetUploadForm();

            })
            .catch(error => {
                 //Si hay algun error se muestra un mensaje diciendo que el turno
                 //no se pudo guardar y se intente nuevamente
                  let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                   document.querySelector('#response').innerHTML = errorAlert;
                   document.querySelector('#response').style.display = "block";
                   //se dejan todos los campos vacíos por si se quiere ingresar otro turno
                   resetUploadForm();})
    });

    function resetUploadForm(){
        document.querySelector('#id').value = "";
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#id_odontologo').value

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/turnosList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});
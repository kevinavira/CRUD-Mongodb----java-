function cargar() {
    var s = "";
   $.get("NewServlet", function (parameters) {
       s = JSON.parse(parameters).Age;
        alert(s);
    });
}

function activarBoton(){
    var valor_select =  document.getElementById("tipo");
    var inputNombre = document.getElementById("newname");
    if (valor_select.value === "update")
    $(inputNombre).removeAttr("disabled");   
    
            
            }
    

    
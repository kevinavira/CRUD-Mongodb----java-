$.get("cargarDatos",  function (parameters) {
    showData("information", parameters);
});
function showData(element, jsonResponseArray) {
    //aca recive el json y lo muestra
    var div = document.getElementById(element);
    for (var runner = 0; runner < jsonResponseArray.length; runner++) {
        $(div).append("<p><-----------------------------------------------></p>");      
        $(div).append("<div><input type='button' onclick='senToServer(" + "\"" + jsonResponseArray[runner]._id.$oid + "\"" + ")' value='" + jsonResponseArray[runner]._id.$oid + "' /></div>");
        $(div).append("<div><input type='button' onclick='envioDato(" + "\"" + jsonResponseArray[runner]._id.$oid + "\"" + ")' value='escoger' /></div>");
        $(div).append("<div>" + jsonResponseArray[runner].Name + "</div>");
        $(div).append("<div>" + jsonResponseArray[runner].Age + "</div>");
        $(div).append("<div>" + jsonResponseArray[runner].fecha + "</div>");
        $(div).append("<div>" + jsonResponseArray[runner].ruta + "</div>");
        $(div).append("<p><-----------------------------------------------></p>");
    }
}

function envioDato(id){
      $("#f1").val(id);
}

function senToServer(id) {
    $.get("Eliminar", {elimarId: id}, function (parameters) {
        s = JSON.parse(parameters).Age;
        alert(s);
    });
}

//--

/*
 var array = [];
 var table = document.createElement("table");
 var caption = document.createElement("caption");
 var thead = document.createElement("thead");
 var tbody = document.createElement("tbody");
 for (var title in json) {
 caption.textContent = title;
 array = json[title];
 //console.log(title);
 }
 table.appendChild(caption);
 
 for (var i = 0; i < array.length; i++) {
 var tr = document.createElement("tr");
 for (var attributes in array[i]) {
 var th = document.createElement("th");
 th.textContent = attributes;
 //console.log(attributes);
 tr.appendChild(th);
 }
 thead.appendChild(tr);
 break;
 }
 table.appendChild(thead);
 
 for (var i = 0; i < array.length; i++) {
 var tr = document.createElement("tr");
 for (var attributes in array[i]) {
 var td = document.createElement("td");
 td.textContent = array[i][attributes];
 tr.appendChild(td);
 //console.log(array[i][attributes]);
 }
 tbody.appendChild(tr);
 }
 table.appendChild(tbody);
 div.appendChild(table);*/
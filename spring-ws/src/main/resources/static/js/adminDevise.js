//callback appelée si code http de retour autre que 200 (ex: 401, 400, 404, 500 , ..)
var traiterError = function(jsonResponse){
    var zoneMessage =  document.getElementById("spanMessage");
    zoneMessage.innerHTML="une erreur a eu lieu";
}
//callback appelée si code http de retour = 200 ok
var traiterReponseAddDevise = function(jsonResponse){
    console.log("jsonResponse="+jsonResponse);
    //var jsResDevise = JSON.parse(jsonResponse);

    var zoneMessage =  document.getElementById("spanMessage");
    zoneMessage.innerHTML=jsonResponse;
}

function onAddDevise(){
    var zoneSaisieCode =  document.getElementById("txtCode");
    var code = zoneSaisieCode.value;
    var zoneSaisieName =  document.getElementById("txtName");
    var name = zoneSaisieName.value;
    var zoneSaisieChange =  document.getElementById("txtChange");
    var change = zoneSaisieChange.value;
    var jsDevise = {
        code : code,
        name : name,
        change : change
    };
    var urlWsPostDevise="./devise-api/private/devise";
    makeAjaxPostRequest(urlWsPostDevise,JSON.stringify(jsDevise) ,traiterReponseAddDevise,
        traiterError); //non bloquant (asynchrone)
    //....
}
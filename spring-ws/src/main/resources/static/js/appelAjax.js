
var traiterReponse = function (response){
    //response ici au format "json string"
    //zoneResultat.innerHTML=response;
    var jsDevise = JSON.parse(response);
    zoneResultat.innerHTML=jsDevise.change; //ou .rate
}

function onSearchDevise(){
    var zoneSaisieCode =
        document.getElementById("txtCodeDevise");
    var zoneResultat =
        document.getElementById("spanRes");
    var codeDevise = zoneSaisieCode.value;
    console.log("codeDevise="+codeDevise);
    var urlWsGet="./devise-api/public/devise/"+codeDevise;
    makeAjaxGetRequest(urlWsGet,traiterReponse); //non bloquant (asynchrone)
    //....
}
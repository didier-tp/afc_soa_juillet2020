
var traiterReponse = function (response){
    //response ici au format "json string"
    var zoneResultat = document.getElementById("spanRes");
    var jsDevise = JSON.parse(response);
    zoneResultat.innerHTML=jsDevise.change; //ou .rate
}

var traiterReponseConv = function (response){
    //response ici au format "json string"
    var zoneResultatConv = document.getElementById("spanResConv");
    var jsResConv = JSON.parse(response);
    zoneResultatConv.innerHTML=jsResConv.result; //ou .montantConverti
}

function onConvertir(){
    //url à appeler en GET:
    //./devise-api/public/devise-convert?src=EUR&target=USD&amount=200
    //et le resultat (JSON) sera de type
    // { "src" : "EUR" , "target" : "USD" , "amount" : 200 , "result" : 210 }
    // qui sera par exemple dto.ResConv en java coté serveur
    var zoneSaisieSrc =  document.getElementById("txtCodeMonnaieSource");
    var src = zoneSaisieSrc.value;
    var zoneSaisieTarget =  document.getElementById("txtCodeMonnaieCible");
    var target = zoneSaisieTarget.value;
    var zoneSaisieAmount =  document.getElementById("txtMontant");
    var amount = zoneSaisieAmount.value;
    var urlWsConvert="./devise-api/public/devise-convert?src="+src+"&target="+target+"&amount="+amount;
    console.log("urlWsConvert="+urlWsConvert);
    makeAjaxGetRequest(urlWsConvert,traiterReponseConv); //non bloquant (asynchrone)
    //....
}

function onSearchDevise(){
    var zoneSaisieCode =  document.getElementById("txtCodeDevise");
    var codeDevise = zoneSaisieCode.value;
    console.log("codeDevise="+codeDevise);
    var urlWsGet="./devise-api/public/devise/"+codeDevise;
    makeAjaxGetRequest(urlWsGet,traiterReponse); //non bloquant (asynchrone)
    //....
}
//callback appelée si code http de retour autre que 200 (ex: 401, 400, 404, 500 , ..)
var traiterLoginError = function(jsonResponse){
    console.log("jsonResponse="+jsonResponse);
    var jsResLogin = JSON.parse(jsonResponse);

    sessionStorage.setItem("token",null);
    var zoneMessage =  document.getElementById("spanMessage");
    zoneMessage.innerHTML=jsResLogin.message;
}
//callback appelée si code http de retour = 200 ok
var traiterReponseLogin = function(jsonResponse){
    console.log("jsonResponse="+jsonResponse);
    var jsResLogin = JSON.parse(jsonResponse);

    if(jsResLogin.ok===true){
        var token = jsResLogin.token;
        //localStorage.setItem("token",token);
        sessionStorage.setItem("token",token);
    }else{
        sessionStorage.setItem("token",null);
    }

    var zoneMessage =  document.getElementById("spanMessage");
    zoneMessage.innerHTML=jsResLogin.message;
}

function onLogin(){
    var zoneSaisieUsername =  document.getElementById("txtUsername");
    var username = zoneSaisieUsername.value;
    var zoneSaisiePassword =  document.getElementById("txtPassword");
    var password = zoneSaisiePassword.value;
    var jsLogin = {
        username : username,
        password : password
    };
    var urlWsLogin="./devise-api/public/login";
    makeAjaxPostRequest(urlWsLogin,JSON.stringify(jsLogin) ,traiterReponseLogin,
        traiterLoginError); //non bloquant (asynchrone)
    //....
}
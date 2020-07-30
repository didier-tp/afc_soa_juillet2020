var express = require('express');
const apiRouter = express.Router();
var request = require("request");

function requestP(url){
	return new Promise((resolve,reject)=>{
		request(url, function(error, response, body) {
			//console.log("status =" + response.statusCode);
			//console.log("body  au format json=" + body);
			if(response.statusCode==200)
				resolve(body);
			else
				reject(body);
		});
	});
}


//exemple URL:  http://localhost:8484/orchestrateur-api/public/propositionPret
               //?nbMois=120&montant=20000
apiRouter.route('/orchestrateur-api/public/propositionPret')
.get( function(req , res  , next ) {
	var nbMois = parseInt(req.query.nbMois);
	var montant = parseInt(req.query.montant);
	var url1 = "http://localhost:8282/taux-api/public/tauxInteretCourant?nbMois="+nbMois;
	var tauxInteretResponseJs ;
	requestP(url1)
		.then((jsonResp1)=>{
			tauxInteretResponseJs = JSON.parse(jsonResp1);
			var url2 = "http://localhost:8282/mensualite-api/public/mensualite?nbMois="
				+nbMois+"&taux=" + tauxInteretResponseJs.tauxInteret+"&montant="
				+ montant;
			return requestP(url2);
		})
		.then((jsonResp2)=>{
			var calculMensualiteResponseJs = JSON.parse(jsonResp2);
			var jsRes = {
				nbMois : nbMois ,	montant : montant ,
				tauxInteret : tauxInteretResponseJs.tauxInteret,
				mensualite : calculMensualiteResponseJs.mensualite,
				fraisDossier : 100.0
			};
			res.send(jsRes);
		})
		.catch((jsonError)=>{ res.status(500).send(jsonError); });
	  //....
});

exports.apiRouter = apiRouter;
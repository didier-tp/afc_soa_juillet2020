var express = require('express');
const apiRouter = express.Router();
//var request = require("request"); //maintenant "deprecated"
const axios = require('axios'); //mieux que request et retournant déja des Promises
//npm install --save axios

//exemple URL:  http://localhost:8484/orchestrateur-api/public/propositionPret
               //?nbMois=120&montant=20000
apiRouter.route('/orchestrateur-api/public/propositionPret')
.get( function(req , res  , next ) {
	var nbMois = parseInt(req.query.nbMois);
	var montant = parseInt(req.query.montant);
	var url1 = "http://localhost:8282/taux-api/public/tauxInteretCourant?nbMois="+nbMois;
	var tauxInteretResponseJs ;
	axios.get(url1)
		.then((httpResp1)=>{
			tauxInteretResponseJs = httpResp1.data;
			var url2 = "http://localhost:8282/mensualite-api/public/mensualite?nbMois="
				+nbMois+"&taux=" + tauxInteretResponseJs.tauxInteret+"&montant="
				+ montant;
			return axios.get(url2);
		})
		.then((httpResp2)=>{
			var calculMensualiteResponseJs = httpResp2.data;
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
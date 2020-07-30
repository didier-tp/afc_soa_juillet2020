var express = require('express');
const apiRouter = express.Router();
//var request = require("request"); //maintenant "deprecated"
const axios = require('axios'); //mieux que request et retournant déja des Promises
//npm install --save axios

//fonction utilitaire pour encapsuler une fonction async
//au sein d'une route express
function asyncToResp(fn) {
	return function(req, res, next) {
		//fn is a alias/reference to a async function (returning data in Promise)
		fn(req, res, next)
			.then((data)=> { res.send(data) }) //retour d'un resultat converti en json
			.catch((err)=>{
				res.status(500)
					.json({errorCode:'500', message: 'Internal Server Error'})
			});
	};
}
/*
utilisation:
app.get("fin_url" , asyncToResp(
                       async  function(req, res,next) {
						   ...;
						   var res1 = await appelAsync1(...);
						   ...;
						   return dataObj; //sera automatiquement transformé en json et renvoyé
					   })
	    );
*/

//exemple URL:  http://localhost:8484/orchestrateur-api/public/propositionPret
               //?nbMois=120&montant=20000
apiRouter.route('/orchestrateur-api/public/propositionPret')
.get(asyncToResp(
	async  function(req, res,next) {
		try {
			var nbMois = parseInt(req.query.nbMois);
			var montant = parseInt(req.query.montant);
			var url1 = "http://localhost:8282/taux-api/public/tauxInteretCourant?nbMois=" + nbMois;
			var httpResp1 = await axios.get(url1);
			var tauxInteretResponseJs = httpResp1.data;
			var url2 = "http://localhost:8282/mensualite-api/public/mensualite?nbMois="
				+ nbMois + "&taux=" + tauxInteretResponseJs.tauxInteret + "&montant=" + montant;
			var httpResp2 = await axios.get(url2);
			var calculMensualiteResponseJs = httpResp2.data;
			var jsRes = {
				nbMois: nbMois, montant: montant,
				tauxInteret: tauxInteretResponseJs.tauxInteret,
				mensualite: calculMensualiteResponseJs.mensualite,
				fraisDossier: 100.0
			};
			return jsRes;
		}catch(ex){
			throw new Error("echec");
		}
}));

exports.apiRouter = apiRouter;
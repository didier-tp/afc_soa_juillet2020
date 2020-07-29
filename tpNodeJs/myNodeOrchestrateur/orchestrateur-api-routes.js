var express = require('express');
const apiRouter = express.Router();

//exemple URL:  http://localhost:8484/orchestrateur-api/public/propositionPret
               //?nbMois=120&montant=20000
apiRouter.route('/orchestrateur-api/public/propositionPret')
.get( function(req , res  , next ) {
	var nbMois = parseInt(req.query.nbMois);
	var montant = parseInt(req.query.montant);
	//.....
	var jsRes = {
		nbMois : nbMois ,
		montant : montant , 
		tauxInteret : 0.9,
		mensualite : 127.34,
		fraisDossier : 100
	};
	res.send(jsRes);
});


exports.apiRouter = apiRouter;
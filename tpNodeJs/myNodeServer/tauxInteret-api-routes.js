var express = require('express');
const apiRouter = express.Router();

//exemple URL:  http://localhost:8282/taux-api/public/tauxInteretCourant?nbMois=120
apiRouter.route('/taux-api/public/tauxInteretCourant')
.get( function(req , res  , next ) {
	var nbMois = parseInt(req.query.nbMois);
	var tauxInteretCourant;
	if(nbMois <= 24 ){ 
		tauxInteretCourant = 1.0;
	}
     else if(nbMois > 24  && nbMois<=60 )  {
		 tauxInteretCourant = 0.7;
	 }
	 else {
		 tauxInteretCourant = 0.8;
	 }
	var jsRes = {
		nbMois : nbMois ,
		tauxInteret : tauxInteretCourant
	};
	res.send(jsRes);
});


exports.apiRouter = apiRouter;
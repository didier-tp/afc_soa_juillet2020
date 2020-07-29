var express = require('express');
const apiRouter = express.Router();

//exemple URL:  http://localhost:8282/mensualite-api/public/mensualite
             // ?nbMois=120&taux=0.9&montant=20000
apiRouter.route('/mensualite-api/public/mensualite')
.get( function(req , res  , next ) {
	var nbMois = parseInt(req.query.nbMois);
	var tauxAnnuelPct = parseFloat(req.query.taux);
	var montant = parseInt(req.query.montant);
	var tauxMensuel = tauxAnnuelPct / 100 / 12;
	var mensualite = montant * tauxMensuel / (1 - Math.pow(1.0+tauxMensuel,-nbMois)) ;
	var jsRes = {
		nbMois : nbMois ,
		taux: tauxAnnuelPct,
		montant : montant,
		mensualite : mensualite
	};
	res.send(jsRes);
});


exports.apiRouter = apiRouter;
var express = require('express');
const apiRouter = express.Router();

var myGenericMongoClient = require('./my_generic_mongo_client');

function replace_mongoId_byCode(devise){
	devise.code = devise._id;
	delete devise._id; 
	return devise;
}

function replace_code_byMongoId(devise){
	devise._id = devise.code;
	delete devise.code; 
	return devise;
}

function replace_mongoId_byCode_inArray(deviseArray){
	for(i in deviseArray){
		replace_mongoId_byCode(deviseArray[i]);
	}
	return deviseArray;
}



//exemple URL: http://localhost:8282/devise-api/public/devise/EUR
apiRouter.route('/devise-api/public/devise/:code')
.get( function(req , res  , next ) {
	var codeDevise = req.params.code;
	myGenericMongoClient.genericFindOne('devises',
										{ '_id' : codeDevise },
									    function(err,devise){
											if(devise==null)
											   res.status(404).send({ err : 'not found'});
											else
										       res.send(replace_mongoId_byCode(devise));
									   });
	
});

//exemple URL: http://localhost:8282/devise-api/public/devise (returning all devises)
//             http://localhost:8282/devise-api/public/devise?changeMini=1.05
apiRouter.route('/devise-api/public/devise')
.get( function(req , res  , next ) {
	var changeMini = Number(req.query.changeMini);
	var mongoQuery = changeMini ? { change: { $gte: changeMini }  } : { } ;
	//console.log("mongoQuery=" + JSON.stringify(mongoQuery));
	myGenericMongoClient.genericFindList('devises',mongoQuery,function(err,devises){
		   res.send(replace_mongoId_byCode_inArray(devises));
	});//end of genericFindList()
});

// http://localhost:8282/devise-api/private/role-admin/devise en mode post
// avec { "code" : "mxy" , "nom" : "monnaieXy" , "change" : 123 } dans req.body
apiRouter.route('/devise-api/private/role-admin/devise')
.post( function(req , res  , next ) {
	var nouvelleDevise = req.body;
	console.log("POST,nouvelleDevise="+JSON.stringify(nouvelleDevise));
	//nouvelleDevise._id=nouvelleDevise.code;
	var nouvelleDevisePourMongoAvecId = replace_code_byMongoId(nouvelleDevise);
	myGenericMongoClient.genericInsertOne('devises',
	                                      nouvelleDevisePourMongoAvecId,
									     function(err,eId){
											 if(err==null && eId !=null)
											   res.send(replace_mongoId_byCode(nouvelleDevise));
											 else 
											   res.status(500).send({err : "cannot insert in database" ,
											                         cause : err});
									    });
});

// http://localhost:8282/devise-api/private/role-admin/devise en mode PUT
// avec { "code" : "USD" , "nom" : "Dollar" , "change" : 1.123 } dans req.body
apiRouter.route('/devise-api/private/role-admin/devise')
.put( function(req , res  , next ) {
	var newValueOfDeviseToUpdate = req.body;
	console.log("PUT,newValueOfDeviseToUpdate="+JSON.stringify(newValueOfDeviseToUpdate));
	myGenericMongoClient.genericUpdateOne('devises',
	newValueOfDeviseToUpdate.code ,
	{ nom : newValueOfDeviseToUpdate.nom , 
	  change : newValueOfDeviseToUpdate.change} ,
	function(err,devise){
			if(err){
				res.status(404).json({ err : "no devise to update with code=" + newValueOfDeviseToUpdate.code });
			}else{
					res.send(newValueOfDeviseToUpdate);
			 }
	});	//end of genericUpdateOne()
});

// http://localhost:8282/devise-api/private/role-admin/devise/EUR en mode DELETE
apiRouter.route('/devise-api/private/role-admin/devise/:code')
.delete( function(req , res  , next ) {
	var codeDevise = req.params.code;
	console.log("DELETE,codeDevise="+codeDevise);
	myGenericMongoClient.genericDeleteOneById('devises', codeDevise ,
									     function(err,isDeleted){
											 if(!isDeleted)
											    res.status(404).send({ err : "not found , no delete" } );
											 else
										        res.send({ deletedDeviseCode : codeDevise } );
									    });
});

exports.apiRouter = apiRouter;
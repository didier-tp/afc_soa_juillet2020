 
		var calcul = require('../calcul')

		
		var chai = require("chai");
		var expect = chai.expect;
		
		describe("my calculator tests", function () {
            var detector;

            beforeEach(function () {
                console.log("initialisation : new ... or ..." );
            });

            it("res_ttc==240?", function () {
                expect(calcul.ttc(200,20)).to.equal(240);
            });         
            
        });

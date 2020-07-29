var calcul = require("./calcul");
var res_ttc = calcul.ttc(200,20);
console.log("ttc="+res_ttc);
var res_tva = calcul.tva(200,20);
console.log("tva="+res_tva);
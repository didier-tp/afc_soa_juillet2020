"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
//real class for instanciation ,  with constructor .
class DeviseObject {
    constructor(code = "?", nom = "?", change = 0) {
        this.code = code;
        this.nom = nom;
        this.change = change;
    }
}
exports.DeviseObject = DeviseObject;
//exemples: ("USD" , "dollar" , 1) ,  ("EUR" , "euro" , 0.9)

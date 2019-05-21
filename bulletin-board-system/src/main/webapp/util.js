// Usage:
// var util = new Util();
//
function Util(){}

Util.prototype.jsonArr2matrix = function(jsonArr, columns){
   // two dimensional array
   var matrix = [];
   for(var i=0, l= jsonArr.length; i<l; i++){
      var row = jsonArr[i];
      var arrRow = [];
      for(var j=0, len=columns.length; j<len; j++){
         arrRow.push(row[columns[j]]);
      }
      matrix.push(arrRow);
   }
   return matrix;
};
var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
};

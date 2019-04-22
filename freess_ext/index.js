const superagent = require('superagent')
const cherrio = require('cheerio')

const translator = { 'IP Address': 'address', 'Port': 'port', 'Password': 'password', 'Method': 'method' }
const C_ISXB_TOP = 'C.ISXB.TOP';

var parseServer =module.exports['parseServer'] = function (serverStr, specified) {
   let server = {}
   serverStr.split('\n').forEach(function (line) {
      let kv = line.split(':')
      if (kv.length === 1) {
         kv = line.split('：')
      }
      if (kv.length > 1) {
         let key = kv[0].trim()
         let val = kv[1].trim()
         let trueKey = translator[key]
         if (trueKey) {
            server[trueKey] = val
         }
      }
   })
   return server
}
module.exports = {

   c_isxb_top: function(){
      return superagent.get('http://ss.ishadowx.com/').then(function (resp) {
         //console.log(resp.text);
         let $ = cherrio.load(resp.text)
         let c_isxb_top_serv = undefined;
         $('.portfolio-items .portfolio-item').each(function (i, elem) {
            //console.log("Processing: ", $(this).text().trim())
            c_isxb_top_serv = parseServer( $(this).text().trim())
            let addr = c_isxb_top_serv.address
            if(undefined != addr && addr.length >0 && addr.toUpperCase() === C_ISXB_TOP){
               return;
            }
         })
         return c_isxb_top_serv;

      });
   }
}

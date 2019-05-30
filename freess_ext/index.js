const superagent = require('superagent')
const cherrio = require('cheerio')
const url = 'https://t.netflybit.top/'

module.exports = {

   fetch: function(){
      return superagent.get(url).then(function (res) {
         let $ = cherrio.load(res.text)
         var resp = {};
         resp.address = $('span#host0').text();
         resp.port= $('span#port0').text();
         resp.password = $('span#pass0').text();
         resp.method = $('span#encrypt0').text();
         return resp;
      })

   }
}

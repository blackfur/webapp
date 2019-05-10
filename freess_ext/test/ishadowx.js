var expect = require('chai').expect;
const superagent = require('superagent')
const cherrio = require('cheerio')
const parseServer = require('../index').parseServer

describe('ishadowx.com', function () {
   it('should provide ss info', function () {

      return superagent.get('http://ss.ishadowx.com/').then(function (resp) {
         //console.log(resp.text);
         let $ = cherrio.load(resp.text)
         $('.portfolio-items .portfolio-item').each(function (i, elem) {
               //console.log("Processing: ", $(this).text().trim())
            console.log("formated: ", parseServer( $(this).text().trim()))
         })
      });
   });
});

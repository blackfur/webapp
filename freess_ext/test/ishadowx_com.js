var expect = require('chai').expect;
const superagent = require('superagent')
const cherrio = require('cheerio')

describe('ishadowx.com', function () {
   it('should provide ss info', function (done) {

      expect(0).to.be.equal(0);
      superagent.get('http://ss.ishadowx.com/').then(function (resp) {
         console.log(resp.text);
         let $ = cherrio.load(resp.text)
         $('.portfolio-items .portfolio-item').each(function (i, elem) {
               console.log($(this).text().trim())
         })
         // important: Use done to chain back(callback chain) When doing async test.
         done();

      });
   });
});

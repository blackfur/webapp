var assert= require('chai').assert;
const fetch = require('../index').fetch

describe('fetch', function () {
   it('should fetch serv info', function () {

      return fetch().then(function(info){
         console.log(info);
      });
   });
});



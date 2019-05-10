var assert= require('chai').assert;
var http = require('http');

describe('async req by Promise in mocha', function () {
   it('should fail by HOST not exists', function () {

      return new Promise((resolve, reject)=>{
         http.get('http://not_found.com/', resp =>{
            resolve(resp);
         });
      }).then(resp => {
         assert.equal(200, resp.statusCode);
      }).catch(err => {
         console.log('requesting notfound.com: ', err.message);
      });
   });
});


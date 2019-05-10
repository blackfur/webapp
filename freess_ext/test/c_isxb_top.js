var assert= require('chai').assert;
const c_isxb_top = require('../index').c_isxb_top

describe('c_isxb_top', function () {
   it('should fetch c_isxb_top serv info', function () {

      return c_isxb_top().then(function(info){
         console.log(info);
         assert(info.address === 'c.isxb.top', 'c.isxb.top should be found.')
      });
   });
});



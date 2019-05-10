var expect = require('chai').expect;
const fss= require('free-shadowsocks')

describe('fss', function () {
   it('should provide ss list', function () {
      return fss().then((fsslist) => {
         fsslist.forEach((info) => {
            console.log(info)
         })
      }, (err) => {
         console.error(err)
      })

   })
})


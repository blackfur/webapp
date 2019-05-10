const translator = { 'IP Address': 'address', 'Port': 'port', 'Password': 'password', 'Method': 'method' }
const C_ISXB_TOP = 'C.ISXB.TOP';

var fss= require('free-shadowsocks');

module.exports = {

   c_isxb_top: function(){
      return fss().then((fsslist) => {
         for(var i=0, l=fsslist.length; i<l; i++){
            let info = fsslist[i]
            //console.log(info)
            if(undefined != info.address && info.address.length >0 && info.address.toUpperCase() === C_ISXB_TOP){
               return info
            }
         }
      }, (err) => {
         console.error(err)
      })
   }
}

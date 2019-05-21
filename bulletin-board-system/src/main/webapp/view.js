// import Util;
var util = new Util();
var _COLUMNS = ['id', 'title', 'author', 'createTime', 'category', 'content'];

var foo = "foo";

$(document).ready( function () {
   $('#poststbl').DataTable(
      {
         paging: true,
         serverSide: true,
         ajax: function(argv, notify, conf){
            $.ajax({
               url: '/posts',
               data: {
                  offset: argv.start,
                  limit: argv.length
               },
               success: function(dat,stat, xhr){
                  if (stat === 'nocontent'){
                     alert(stat);
                     return;
                  }
                  notify({
                    data: util.jsonArr2matrix(dat.payload, _COLUMNS)
                  });
               },
               error: function(xhr, stat){
                  alert(xhr.status);
               }
            });
         }
      }
   );
} );

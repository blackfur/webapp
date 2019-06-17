// opt: root, item(/path/to/html), adapter, loader
function List(opt){
   $.get(opt.item)
   .then(function(html){
      opt.loader(
         function(arr){
            for(var i=0,l=arr.length; i<l; i++){
               var tmp = $(html);
               opt.adapter(arr[i], tmp);
               $(opt.root).append(tmp);
            }
         }
      );
   });
}

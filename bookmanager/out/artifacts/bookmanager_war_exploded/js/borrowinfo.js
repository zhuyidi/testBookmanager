/**
 * Created by dela on 1/6/18.
 */
function load(){
    $.ajax({
        type:"post",//请求方式
        url:"servlet/AccountInfo",//发送请求地址
        dataType:"json",
        data:{//发送给数据库的数据
        },
        //请求成功后的回调函数有两个参数
        success:function(data,textStatus){
            var objs=eval(data); //解析json对象
            var obj = objs[0];

            var table = $("#table");
            table.empty();
            table.append('<tr><th></th><th>类别</th><th>文件个数</th><th>文件大小</th><th>时间范围</th></tr>');

            if(obj==null || obj==""){
                table.append('<tr><td colspan="5" style="text-align: center; color:red">暂无数据！</td></tr>')
                //$("#page").hide();
                return false;
            }

            var categorys = obj.categorys;
            for(var i=0;i<categorys.length;i++){
                var type = categorys[i].type;
                var subObjects = categorys[i].subObjects;
                var len = subObjects.length;
                table.append('<tr><td rowspan="'+len+'">'+type+'</td>'+'<td>'+subObjects[0].subtype+'</td>'+'<td>'+subObjects[0].fileCount+'</td>'+'<td>'+subObjects[0].bytes+'</td>'+'<td>'+subObjects[0].timeRange+'</td></tr>')
                //table.append('<td>'+subObjects[0].subtype+'</td>'+'<td>'+subObjects[0].fileCount+'</td>'+'<td>'+subObjects[0].bytes+'</td>'+'<td>'+subObjects[0].timeRange+'</td></tr>');
                for(var j=1;j<len;j++){
                    table.append('<tr><td>'+subObjects[j].subtype+'</td>'+'<td>'+subObjects[j].fileCount+'</td>'+'<td>'+subObjects[j].bytes+'</td>'+'<td>'+subObjects[j].timeRange+'</td></tr>');
                }
            }

            //为鼠标移动添加样式，鼠标所在行变色，鼠标离开行恢复原状
            $("tr:even").addClass("even");
            $("th").first().css("text-align","left");
            $("th").first().css("padding-left","5px");
            $("tr").mouseenter(function(){
                $(this).addClass('bs');
            });
            $("tr").mouseleave(function(){
                $(this).removeClass('bs');
            });
        }
    });
}
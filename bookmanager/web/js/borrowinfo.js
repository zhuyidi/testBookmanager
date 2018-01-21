/**
 * Created by dela on 1/6/18.
 */
function load(page){
    $.ajax({
        type:"post",//请求方式
        url:"/borrowinfo/" + page,//发送请求地址
        dataType:"text",
        data:{//发送给数据库的数据
        },
        //请求成功后的回调函数有两个参数
        success:function(data){
            console.log(arguments);
            // for (var i in data) {
            //     $("#" + i).text(data.i);
            // }

            $("#time0").text(data.time0);
            $("#message0").text(data.message0);

            $("#time1").text(data.time1);
            $("#message1").text(data.message1);
            $("#time2").text(data.time2);
            $("#message2").text(data.message2);
            $("#time3").text(data.time3);
            $("#message3").text(data.message3);
            $("#time4").text(data.time4);
            $("#message4").text(data.message4);
            $("#time5").text(data.time5);
            $("#message5").text(data.message5);
            $("#time6").text(data.time6);
            $("#message6").text(data.message6);
            $("#time7").text(data.time7);
            $("#message7").text(data.message7);
            $("#time8").text(data.time8);
            $("#message8").text(data.message8);
            $("#time9").text(data.time9);
            $("#message9").text(data.message9);
        }
    });
}
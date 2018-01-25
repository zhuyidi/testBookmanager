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
            var data =arguments[0];
         
            console.log(arguments);
            // for (var i in data) {
            //     $("#" + i).text(data.i);
            // }
            $("#time1").innerText(data[1].time0);
            $("#message1").innerText(data[1].message0);


            $("#time0").text(data[0].time0);
            $("#message0").text(data[0].message0);

            // $("#time1").text(data[1].time1);
            // $("#message1").text(data[1].message1);
            $("#time2").text(data[2].time2);
            $("#message2").text(data[2].message2);
            $("#time3").text(data[3].time3);
            $("#message3").text(data[3].message3);
            $("#time4").text(data[4].time4);
            $("#message4").text(data[4].message4);
            $("#time5").text(data[5].time5);
            $("#message5").text(data[5].message5);
            $("#time6").text(data[6].time6);
            $("#message6").text(data[6].message6);
            $("#time7").text(data[7].time7);
            $("#message7").text(data[7].message7);
            $("#time8").text(data[8].time8);
            $("#message8").text(data[8].message8);
            $("#time9").text(data[9].time9);
            $("#message9").text(data[9].message9);
        }
    });
}
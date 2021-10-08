$(function () {
    islogin();

    /*我发起的活动界面跳转*/
    $('#myactivity').click(function () {
        $.ajax({
            url: "/user/useriflogin",
            type: "POST",
            async: false,
            success: function (data) {
                if (data['iflogin'] === "userislogin") {
                    window.location.href = "/activity/myactivity";
                } else {
                    window.location.href = "";
                    layer.msg("请先登陆");
                }
            }
        });
    });

    /*我参与的活动界面跳转*/
    $('#joinactivity').click(function () {
        $.ajax({
            url: "/user/useriflogin",
            type: "POST",
            async: false,
            success: function (data) {
                if (data['iflogin'] === "userislogin") {
                    window.location.href = "/activity/myjoinactivity";
                } else {
                    window.location.href = "";
                    layer.msg("请先登陆");
                }
            }
        });
    });

    /*用户搜索*/
    $('#searchbtn').click(function () {
        window.location.href = "/user/searchhome";
    });

});

/*后台返回json，得到用户是否登陆，并显示用户信息框*/
function islogin() {
    $.ajax({
        url: "/user/useriflogin",
        type: "POST",
        async:false,
        success: function (data) {

            var htm = "";

            if (data['iflogin'] === "usernologin") {

                htm = `<ul>
                           <li>
                               <div class="body"><a href="/user/userlogin">点击前往登陆</a></div>
                           </li>

                       </ul>`;

            } else if (data['iflogin'] === "userislogin") {

                htm = `<table class="bookinfo">
                           <tr>
                               <td rowspan ="2">
                                   <div style="border: silver 5px solid;">
                                        <img src="/prc/headprc/${data['userdata'].headprc}.jpg" height="100" width="100"/>
                                    </div>
                               </td>
                               <td>
                                   <ul style="list-style-type: none;">
                                        <li>用户：<span>${data['userdata'].username}</span></li>
                                        <li>昵称：<span>${data['userdata'].nickname}</span></li>
                                        <li>性别：<span>${data['userdata'].sex}</span></li>
                                        <li>电话：<span>${data['userdata'].phone}</span></li>
                                        <li><a href="/user/logout" style="color: red;">退出登陆</a></li>
                                    </ul>
                                </td>
                            </tr>
                        </table>`;
            }

            $('#showuserinfo').html(htm);
        }
    });
}
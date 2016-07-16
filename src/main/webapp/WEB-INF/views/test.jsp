<%--
  Created by IntelliJ IDEA.
  User: wayne
  Date: 2016. 6. 30.
  Time: 오후 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--main content--%>
<html>
<head>
    <!-- Bootstrap 3.3.4 -->
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- Theme style -->
    <link href="/resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link href="/resources/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h2>Ajax Test Page</h2>

<div>
    <div>
        REPLYER <input type="text" name="replyer" id="newReplyWriter">
    </div>
    <div>
        REPLY TEXT <input type="text" name="replytext" id="newReplyText">
    </div>
    <button id="replyAddBtn">ADD REPLY</button>
</div>

<ul id="replies">
</ul>

<ul class="pagination"></ul>

<div id="modDiv">
    <div class="modal-title"></div>
    <div>
        <input type="text" id="replytext">
    </div>
    <div>
        <button type="button" id="replyModBtn">Modify</button>
        <button type="button" id="replyDelBtn">Delete</button>
        <button type="button" id="closeBtn">Close</button>
    </div>
</div>


<script src="../../resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script type="text/javascript">
    var bno = 200;
    var replyPage = 1;
    getPageList(replyPage);

    function getAllList() {
        $.getJSON("/replies/all/" + bno, function (data) {
            console.log(data.length);

            var str = "";
            $(data).each(function () {
                str += "<li data-rno='" + this.rno + "' class='replyLi'>"
                        + this.rno + ":" + this.replytext + "<button>MOD</button></li>";
            });

            $("#replies").html(str);
        });
    }

    function getPageList(page) {
        $.getJSON("/replies/" + bno + "/" + page, function (data) {
            console.log(data.list.length);

            var str = "";

            $(data.list).each(function () {
                str += "<li data-rno='" + this.rno + "' class='replyLi'>"
                + this.rno + ":" + this.replytext
                + "<button>MOD</button></li>";
            });

            $("#replies").html(str);

            printPaging(data.pageMaker);
        });
    }

    function printPaging(pageMaker) {

        var str = "";
        if (pageMaker.prev) {
            str += "<li><a href='" + (pageMaker.startPage - 1) + "'> << </a></li>";
        }

        for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
            var strClass = pageMaker.cri.page === i ? 'class=active' : '';
            str += "<li " + strClass + "><a href='" + i + "'>" + i + "</a></li>"
        }

        if (pageMaker.next) {
            str += "<li><a href='" + (pageMaker.endPage + 1) + "'> >> </a></li>";
        }

        $(".pagination").html(str);
    }
    $("#replyAddBtn").on("click", function () {
        var replyer = $("#newReplyWriter").val();
        var replytext = $("#newReplyText").val();

        $.ajax({
            type: 'post',
            url: '/replies',
            headers: {
                "Content-type": "application/json",
                "X-HTTP-Method-Override": "POST"
            },
            dataType: 'text',
            data: JSON.stringify({
                bno: bno,
                replyer: replyer,
                replytext: replytext
            }),
            success: function (result) {
                if (result === 'SUCCESS') {
//                    alert('등록되었습니다.');
                    replyPage = 1;
                    getPageList(replyPage);
                }
            }
        })
    });



    $("#replyDelBtn").on("click", function () {
        var rno = $(".modal-title").html();
        var replytext = $("#replytext").val();

        $.ajax({
            type: 'delete',
            url: '/replies/' + rno,
            headers: {
                "Content-type": "application/json",
                "X-HTTP-Method-Override": "DELETE"
            },
            dataType: 'text',
            success: function (result) {
                console.log(result);
                if (result === "SUCCESS") {
//                    alert("삭제되었습니다.");
                    $("#modDiv").hide("slow");
                    getPageList(replyPage);
                }
            }
        });
    });

    $("#replyModBtn").on("click", function () {
        var rno = $(".modal-title").html();
        var replytext = $("#replytext").val();

        $.ajax({
            type: 'put',
            url: '/replies/' + rno,
            headers: {
                "Content-type": "application/json",
                "X-HTTP-Method-Override": "PUT"
            },
            data: JSON.stringify({
                replytext: replytext
            }),
            dataType: 'text',
            success: function (result) {
                console.log(result);
                if (result === "SUCCESS") {
//                    alert("수정되었습니다.");
                    $("#modDiv").hide("slow");
                    getPageList(replyPage);
                }
            }
        });
    })

    $("#closeBtn").on("click", function () {
        $("#modDiv").hide("slow");
    });

    $("#replies").on("click", ".replyLi button", function () {
        var reply = $(this).parent();
        var rno = reply.attr('data-rno');
        var replytext = reply.text();

        $(".modal-title").html(rno);
        $("#replytext").val(replytext);
        $("#modDiv").show("slow");
    });

    $(".pagination").on("click", "li a", function (event) {
        event.preventDefault();

        replyPage = $(this).attr("href");

        getPageList(replyPage);
    });

</script>
<style>
    #modDiv {
        width: 300px;
        height: 100px;
        background-color: grey;
        position: absolute;
        top: 50%;
        left: 50%;
        margin-top: -50px;
        margin-left: -150px;
        padding: 10px;
        z-index: 1000;
        display: none;
    }
</style>
</body>
</html>
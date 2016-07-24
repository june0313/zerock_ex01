<%--
  Created by IntelliJ IDEA.
  User: wayne
  Date: 2016. 7. 24.
  Time: 오후 5:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .fileDrop {
            width: 100%;
            height: 200px;
            border: 1px dotted lightslategray;
        }

        small {
            margin-left: 3px;
            font-weight: bold;
            color: grey;
        }
    </style>
</head>
<body>

<h3>Ajax File Upload</h3>
<div class="fileDrop"></div>

<div class="uploadList"></div>

<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script>
    $(".fileDrop").on("dragenter dragover", function (event) {
        event.preventDefault();
    });

    $(".fileDrop").on("drop", function (event) {
        event.preventDefault();

        var files = event.originalEvent.dataTransfer.files;
        var file = files[0];
        console.log(file);

        // FormData는 IE의 경우 10부터 지원한다.
        var formData = new FormData();
        formData.append("file", file);

        $.ajax({
            url: '/uploadAjax',
            data: formData,
            dataType: 'text',
            processData: false, // jquery의 ajax를 이용해 FormData를 전송하려면 반드시 false로 설정해야 한다.
            contentType: false, // jquery의 ajax를 이용해 FormData를 전송하려면 반드시 false로 설정해야 한다.
            type: 'POST',
            success: function(data) {
                alert(data);
            }
        });
    });
</script>
</body>
</html>

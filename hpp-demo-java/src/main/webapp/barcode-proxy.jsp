<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>微信扫码支付二维码</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.css"/>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div id="code"></div>
        </div>
    </div>
</div>

<script src="http://cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
<script src="/js/jquery.qrcode.min.js"></script>
<script>
    $(function () {
        $("#code").qrcode({
            render: "table", //table方式
            width: 300, //宽度
            height: 300, //高度
            text: '${ code_url }' //任意内容
        });
    });
</script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Oops</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/sorttable.js"
            language="JavaScript"></script>
</head>
<body>
<style>
    * {
        margin: 0;
        padding: 0;
    }

    .container {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        margin: 100px;
        max-width: 700px;
        height: 650px;
        border-radius: 45px;
        background-color: #a0a0a0;
    }

    .btn {
        margin-top: 10px;
        padding: 10px;
        width: 300px;
        font-size: 22px;
    }

    .text-center {
        margin-bottom: 20px;
        color: #ffffff;
    }

    .background {
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .oops {
        margin-bottom: 25px;
        height: 250px;
    }
</style>
<div class="background">
    <div class="container">
        <h1 class="text-center">Oops! something bad happened</h1><br>
        <img class="oops" src="https://www.pngkit.com/png/full/125-1258059_sorry-cat-cartoon.png">
        <h4 class="text-center">It looks like something bad happened on this page.</h4>
        <h4 class="text-center">Please got to main page and try again</h4>
        <a href="login.jsp" class="btn btn-primary"> Go back to main page </a>
    </div>
</div>
</body>
</html>
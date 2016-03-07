<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Шоппинг!</title>
    <link href="<c:url value='/static/css/normalize.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/static/bower_components/bootstrap/dist/css/bootstrap.min.css'/>"  rel="stylesheet"/>
    <link href="<c:url value='/static/bower_components/font-awesome/css/font-awesome.min.css'/>"  rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css'/>" rel="stylesheet"/>
</head>
<body>

<c:url var="loginUrl" value="/login" />

<div id="mainWrapper">
    <div class="container container-fluid login-container">
        <div class="row">
            <div class="col-xs-1 col-md-4"></div>
            <div class="col-xs-10 col-md-4">
                <div class="login-form">
                    <h1>Добро пожаловать</h1>
                    <form action="${loginUrl}" method="post" class="form-horizontal">
                        <c:if test="${param.error != null}">
                            <div class="alert alert-danger">
                                <p>Неправильные данные!</p>
                            </div>
                        </c:if>
                        <div class="input-group input-sm">
                            <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                            <input type="text" class="form-control" id="username" name="username" placeholder="Имя" required>
                        </div>
                        <div class="input-group input-sm">
                            <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="Пароль" required>
                        </div>
                        <%--<input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />--%>
                        <div class="form-actions">
                            <input type="submit"
                                   class="btn btn-block btn-primary btn-default" value="Войти">
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-xs-1 col-md-4"></div>
        </div>
    </div>
</div>

<script type="application/javascript" src="<c:url value='/static/bower_components/jquery/dist/jquery.min.js'/>"></script>
<script type="application/javascript" src="<c:url value='/static/bower_components/bootstrap/dist/js/bootstrap.min.js'/>"></script>

</body>
</html>

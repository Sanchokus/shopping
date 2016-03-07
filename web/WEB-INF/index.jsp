<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
    <title>Шоппинг!</title>
    <link href="<c:url value='/static/css/normalize.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/static/bower_components/bootstrap/dist/css/bootstrap.min.css'/>"  rel="stylesheet"/>
    <link href="<c:url value='/static/bower_components/font-awesome/css/font-awesome.min.css'/>"  rel="stylesheet"/>
    <link href="<c:url value='/static/bower_components/ng-dialog/css/ngDialog.min.css'/>"  rel="stylesheet"/>
    <link href="<c:url value='/static/bower_components/ng-dialog/css/ngDialog-theme-default.min.css'/>"  rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css'/>" rel="stylesheet"/>
</head>
<body data-ng-app="shopping" data-ng-controller="shoppingCtrl" ng-cloak>

<div id="main-container" class="container-fluid">
    <div class="row">
        <div class="col-xs-1 col-md-4"></div>
        <div class="col-xs- col-md-4">
            <table class="table table-bordered table-responsive table-striped" id="item-table">
                <thead>
                <tr>
                    <td class="item-table-cell">Shopping</td>
                </tr>
                </thead>
                <tbody>
                <tr class="clickable-row" data-ng-repeat="item in items" data-ng-click="changeSelection(item)"
                    data-ng-class="item.selected ? 'selected-row' : 'unselected-row'">
                    <td class="item-table-cell">
                        {{ item.name }}
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="col-xs-1 col-md-4"></div>
    </div>
</div>

<div id="loading-overlay" data-ng-show="isLoading"></div>

<button type="button" id="round-button-delete" data-ng-click="clickDeleteSelected()" data-ng-disabled="!someItemsAreSelected()" class="btn btn-danger btn-lg btn-circle"><span class="glyphicon glyphicon-trash"></span></button>
<button type="button" id="round-button-add" data-ng-click="clickAdd()" class="btn btn-success btn-lg btn-circle"><span class="glyphicon glyphicon-plus"></span></button>
<button type="button" id="round-button-refresh" data-ng-click="refreshProducts()" class="btn btn-info btn-lg btn-circle"><span class="glyphicon glyphicon-refresh"></span></button>

<script type="text/ng-template" id="addNewModal">
    <div class="ngdialog-message">
        <h5>Добавляем!</h5>
        <form role="form" ng-submit="addNewItem(name)">
            <div class="form-group">
                <input type="text" class="form-control" ng-model="name" maxlength="50"/>
            </div>
        </form>
    </div>
    <div class="ngdialog-buttons">
        <button type="button" class="ngdialog-button ngdialog-button-primary" ng-click="addNewItem(name)">Добавить</button>
    </div>
</script>

<script type="text/ng-template" id="confirmDeleteModal">
    <div class="ngdialog-message">
        <h5>Удаляем?</h5>
        <form role="form">
        </form>
    </div>
    <div class="ngdialog-buttons">
        <button type="button" class="ngdialog-button ngdialog-button-primary" ng-click="deleteSelected()">ОК</button>
    </div>
</script>

<script type="text/ng-template" id="errorModal">
    <div class="ngdialog-message">
        <h1>Ошибка!</h1>
        {{ errorModalText }}
        <p><strong>Обратитесь к админу за пояснениями.</strong></p>
    </div>
</script>

<script type="application/javascript" src="<c:url value='/static/bower_components/jquery/dist/jquery.min.js'/>"></script>
<script type="application/javascript" src="<c:url value='/static/bower_components/bootstrap/dist/js/bootstrap.min.js'/>"></script>
<script type="application/javascript" src="<c:url value='/static/bower_components/angular/angular.min.js'/>"></script>
<script type="application/javascript" src="<c:url value='/static/bower_components/angular-cookies/angular-cookies.min.js'/>"></script>
<script type="application/javascript" src="<c:url value='/static/bower_components/ng-dialog/js/ngDialog.min.js'/>"></script>
<script type="application/javascript" src="<c:url value='/static/js/app.js'/>"></script>
</body>
</html>

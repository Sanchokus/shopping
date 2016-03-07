
angular.module('shopping', ['ngCookies', 'ngDialog'])
    .controller('shoppingCtrl', function($scope, $http, $log, $cookies, ngDialog) {
        'use strict';

        $scope.items = [];
        $scope.errorModalText = '';
        $scope.changeSelection = function(item) {
            item.selected = !item.selected;
        };

        $scope.isLoading = false;

        $scope.refreshProducts = function() {
            $scope.isLoading = true;
            $http.get("product")
                .then(function(response) {
                        if(response.status = 200) {
                            $scope.items = response.data;
                        }
                        else {
                            $scope.items = [];
                        }
                        $scope.isLoading = false;
                    },
                    function(error) {
                        $scope.isLoading = false;
                        $scope.openErrorModal(error.data.message);
                    });
        };
        $scope.openErrorModal = function(text) {
            $scope.errorModalText = text;
            ngDialog.open({
                template: 'errorModal',
                className: 'ngdialog-theme-default',
                scope: $scope
            });
        };
        $scope.clickAdd = function() {
            ngDialog.open({
                template: 'addNewModal',
                className: 'ngdialog-theme-default',
                scope: $scope
            });
        };
        $scope.clickDeleteSelected = function() {
            ngDialog.open({
                template: 'confirmDeleteModal',
                className: 'ngdialog-theme-default',
                scope: $scope
            });
        };
        $scope.addNewItem = function(name) {
            ngDialog.closeAll();
            if(!name) {
                return;
            }
            $scope.isLoading = true;
            $http({ url: 'product',
                method: 'POST',
                data: {name: name},
                headers: {
                    "Content-Type": "application/json;charset=utf-8"
                }
            }).then(function(response) {
                if(response.status != 200) {
                    $scope.openErrorModal(response.data.message);
                    $scope.isLoading = false;
                }
                else {
                    $scope.refreshProducts();
                }
            }, function(error) {
                $scope.isLoading = false;
                $scope.openErrorModal(error.data.message);
            });
        };
        $scope.someItemsAreSelected = function() {
            for(var i=0;i<$scope.items.length;i++) {
                if($scope.items[i].selected) {
                    return true;
                }
            }
            return false;
        };
        $scope.deleteSelected = function() {
            ngDialog.closeAll();
            $scope.isLoading = true;
            for(var i=0;i<$scope.items.length;i++) {
                var item = $scope.items[i];
                if(item.selected) {
                    $http({ url: 'product',
                        method: 'DELETE',
                        data: item,
                        headers: {
                            "Content-Type": "application/json;charset=utf-8"
                        }
                    }).then(function(response) {
                        if(response.status != 200) {
                            $log.log(response.data.message);
                        }
                        $scope.refreshProducts();
                    }, function(error) {
                        $scope.openErrorModal(error.data.message);
                        $scope.refreshProducts();
                    });
                }
            }
        };

        $scope.refreshProducts();
    });

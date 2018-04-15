var myApp = angular.module('App', []);

myApp.controller('testController', function ($scope, $http, $window){

    $scope.login = function(){
        $http({
            method: 'POST',
            url: 'http://localhost:4567/login',
            data: $.param({
                username: $('#username').val(),
                password: $('#password').val()
            }),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function successCallback(response) {
            if(response.data === "Failed"){
                alert("Username or password is incorrect!")
            }
            else {
                $window.location.href='http://localhost:4567/panel';
            }
        });
    };


});
var indexApp = angular.module('indexApp', []);

indexApp.controller('indexController', function ($scope, $http, $window){

    $scope.login = function(username, password){
        $http({
            method: 'POST',
            url: 'http://localhost:4567/login',
            data: $.param({
                username: username,
                password: password
            }),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function successCallback(response) {
            if(response.data === "Failed"){
                alert("Username or password is incorrect!")
            }
            else {
                $window.location.href='/panel';
            }
        });
    };

    $scope.redirect = function() {
        $window.location.href='http://localhost:4567/registration';
    }
});
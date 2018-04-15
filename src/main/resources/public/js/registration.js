var myApp = angular.module('App', []);

myApp.controller('testController', function ($scope, $http, $window){

    $scope.signup = function(){
        $http({
            method: 'POST',
            url: 'http://localhost:4567/registration/signup',
            data: $.param({
                username: $scope.username,
                password: $scope.password,
                email: $scope.email,
                jmbg: $scope.jmbg,
                fullname: $scope.fullname,
                index: $scope.index
            }),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function successCallback(response) {
            $window.location.href='http://localhost:4567/index';
        });
    };


});
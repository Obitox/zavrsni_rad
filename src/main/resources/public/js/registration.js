var myApp = angular.module('App', []);

myApp.controller('testController', function ($scope, $http, $window){

    $scope.signup = function(username, password, fullname, jmbg, address, email, phone){
        $http({
            method: 'POST',
            url: 'http://localhost:4567/registration/signup',
            data: $.param({
                username: username,
                password: password,
                fullname: fullname,
                jmbg: jmbg,
                address: address,
                email: email,
                phone: phone
            }),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function successCallback(response) {
            $window.location.href='http://localhost:4567/index';
        });
    };


});
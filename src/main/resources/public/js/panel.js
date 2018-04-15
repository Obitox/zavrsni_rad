var myApp = angular.module('App', []);

myApp.controller('testController', function ($scope, $http){
    $scope.list = [];

    $scope.initElements = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:4567/panel/data',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function successCallback(response) {
            $scope.list = response.data;
        });

    };

    $scope.updateStudent = function(){
        $http({
            method: 'PUT',
            url: 'http://localhost:4567/panel/data/update',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: $.param({
                student_id: $('#student_id').val(),
                username: $('#username').val(),
                password: $('#password').val(),
                email: $('#email').val(),
                jmbg: $('#jmbg').val(),
                fullname: $('#fullname').val(),
                index: $('#index').val()
            })
        }).then(function successCallback(response) {
            alert(response.data);
        });
    };

    $scope.deleteStudent = function(id){
        $http({
            method: 'DELETE',
            url: 'http://localhost:4567/panel/data/delete/'+id,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function successCallback(response) {
            $scope.initElements();
            alert(response.data);
        });
    };

    $scope.createStudent = function(){
        $http({
            method: 'POST',
            url: 'http://localhost:4567/panel/data/create',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: $.param({
                username: $('#username_create').val(),
                password: $('#password_create').val(),
                email: $('#email_create').val(),
                jmbg: $('#jmbg_create').val(),
                fullname: $('#fullname_create').val(),
                index: $('#index_create').val()
            })
        }).then(function succesCallback(response){
            if(response.data === "Successful"){
                $scope.initElements();
            }
            alert(response.data)
        });
    };

    $scope.options = function(){
        $http({
            method: 'OPTIONS',
            url: 'http://localhost:4567/panel/data/options',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function succesCallback(response){
            alert(response.data)
        });
    };

    $scope.initElements();

});
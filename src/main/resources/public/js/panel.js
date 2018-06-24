var panelController = angular.module('panelApp', []);

panelController.controller('panelController', function ($scope, $http, $window){
    $scope.list = [];

    $scope.id_copy = 0;
    $scope.username_copy = "";
    $scope.password_copy = "";
    $scope.email_copy = "";
    $scope.jmbg_copy = "";
    $scope.fullname_copy = "";
    $scope.index_copy = "";
    $scope.address_copy = "";

    $scope.initElements = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:4567/panel/data',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function successCallback(response) {
            $scope.list = response.data;
        });

    };

    $scope.updateStudent = function(id, username, password, email, jmbg, fullname, index, address){
        $http({
            method: 'PUT',
            url: 'http://localhost:4567/panel/data/update',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: $.param({
                student_id: id,
                username: username,
                password: password,
                email: email,
                jmbg: jmbg,
                fullname: fullname,
                index: index,
                address: address
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
                index: $('#index_create').val(),
                address: $('#address_create').val()
            })
        }).then(function succesCallback(response){
            if(response.data === "Successful"){
                $scope.initElements();
            }
            alert(response.data)
        });
    };

    $scope.logout = function(){
        $http({
            method: 'GET',
            url: 'http://localhost:4567/panel/logout',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function successCallback(response) {
            $window.location.href='/panel/logout';
        });
    };

    $scope.initElements();

});
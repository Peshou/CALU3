AngularApp
    .controller('RegisterController',['$scope','Auth', function ($scope, Auth) {
        $scope.success = null;
        $scope.error = null;
        $scope.doNotMatch = null;
        $scope.errorUserExists = null;
        $scope.registerAccount = {};

        $scope.register = function () {
            if ($scope.registerAccount.password !== $scope.confirmPassword) {
                $scope.doNotMatch = 'ERROR';
            } else {
                $scope.doNotMatch = null;
                $scope.error = null;
                $scope.errorUserExists = null;
                $scope.errorEmailExists = null;

                Auth.createAccount($scope.registerAccount).then(function () {
                    $scope.success = 'OK';
                }).catch(function (response) {
                    $scope.success = null;
                    if (response.status === 400 && response.data === 'username is already in use') {
                        $scope.errorUserExists = 'ERROR';
                        angular.element('[ng-model="registerAccount.username"]').focus();
                    } else if (response.status === 400 && response.data === 'e-mail address is already in use') {
                        $scope.errorEmailExists = 'ERROR';
                        angular.element('[ng-model="registerAccount.email"]').focus();
                    } else {
                        $scope.error = 'ERROR';
                        angular.element('[ng-model="registerAccount.username"]').focus();
                    }
                });
            }
        };
    }]);

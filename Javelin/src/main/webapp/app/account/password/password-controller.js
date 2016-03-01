AngularApp
    .controller('PasswordController',['$scope','Auth','Principal', function ($scope, Auth, Principal) {
        Principal.identity().then(function(account) {
            $scope.account = account;
        });

        $scope.success = null;
        $scope.error = null;
        $scope.doNotMatch = null;
        $scope.oldPasswordWrong = null;
        $scope.changePassword = function () {
            if ($scope.password !== $scope.confirmPassword) {
                $scope.error = null;
                $scope.success = null;
                $scope.doNotMatch = 'ERROR';
            } else {
                $scope.doNotMatch = null;
                $scope.oldAndNew={
                    oldPassword: $scope.oldPassword,
                    newPassword: $scope.password
                }
               /* Auth.changePassword($scope.oldAndNew).then(function () {
                    $scope.error = null;
                    $scope.success = 'OK';
                }).catch(function () {
                    $scope.success = null;
                    $scope.error = 'ERROR';
                    $scope.oldPasswordWrong = 'ERROR';
                });*/
                Auth.changePassword($scope.oldAndNew).then(function () {
                    $scope.error = null;
                    $scope.oldPasswordWrong = null;
                    $scope.success = 'OK';
                }).catch(function (err) {
                    console.log(err);
                    $scope.success = null;
                    $scope.error = 'ERROR';
                    $scope.oldPasswordWrong = 'ERROR';
                });
            }
        };
    }]);

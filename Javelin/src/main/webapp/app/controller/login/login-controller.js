AngularApp.controller('LoginController', [
    '$rootScope',
    '$scope',
    '$state',
    'Auth',
    function ($rootScope, $scope, $state, Auth) {
        $scope.user = {};
        $scope.errors = {};

        $scope.login = function (event) {
            event.preventDefault();
            Auth.login({
                username: $scope.username,
                password: $scope.password,
                rememberMe: $scope.rememberMe
            }).then(function () {
                $scope.authenticationError = false;
                if ($rootScope.previousStateName === 'register') {
                    $state.go('home');
                } else {
                    $rootScope.back();
                }
            }).catch(function (err) {
                $scope.authenticationError = true;
                angular.element('[ng-model="username"]').focus();
            });
        };
    }]);

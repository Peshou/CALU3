AngularApp.controller('NavbarController',
    ['$scope',
        '$location',
        '$state',
        'Auth',
        'Principal',
        function ($scope, $location, $state, Auth, Principal) {
            $scope.isAuthenticated = Principal.isAuthenticated;
            $scope.$state = $state;

            $scope.logout = function () {
                Auth.logout();
                $state.go('home');
            };
        }]);

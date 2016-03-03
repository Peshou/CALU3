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
            $scope.search = function (text) {
                if(text.trim().length > 0){
                    $state.go("search",({searchInput: $scope.searchText}));
                }
                console.log(text);
            }
        }]);

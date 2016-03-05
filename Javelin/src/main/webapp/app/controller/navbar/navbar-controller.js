AngularApp.controller('NavbarController',
    ['$scope',
        '$location',
        '$state',
        'Auth',
        'Principal',
        function ($scope, $location, $state, Auth, Principal) {
            $scope.isAuthenticated = Principal.isAuthenticated;
            $scope.$state = $state;
            $scope.searchInput = null;
            $scope.logout = function () {
                Auth.logout();
                $scope.$state.go('home');
            };
            $scope.search = function (text) {
                if (text != undefined) {
                    if ($scope.$state.current.name == 'allBlogs') {
                        $state.transitionTo('allBlogs', {searchInput: $scope.searchText}, {reload: true, notify: true});
                    } else {
                        $state.go('allBlogs', {searchInput: $scope.searchText});
                    }
                }
            };


            $scope.visible = function () {
                $('#liAdmin').removeClass('hidden');
                Principal.hasAuthority("ROLE_ADMIN")
                    .then(function (result) {
                        if (result) {
                            $('#liAdmin').removeClass('hidden');
                        } else {
                            $('#liAdmin').addClass('hidden');
                        }
                    });
            };

            $scope.$watch(function () {
                return Principal.isAuthenticated();
            }, function (newValue) {
                $scope.visible();
            });
        }]);

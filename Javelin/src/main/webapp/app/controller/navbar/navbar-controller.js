AngularApp.controller('NavbarController',
    ['$scope',
        '$location',
        '$state',
        'Auth',
        'Principal',
        function ($scope, $location, $state, Auth, Principal) {
            $scope.isAuthenticated = Principal.isAuthenticated;
          //  console.log($scope.isAuthenticated());
            $scope.$state = $state;

            $scope.logout = function () {
                Auth.logout();
                $state.go('home');
            };
            $scope.search = function (text) {
                if (text !== undefined && text.trim().length > 0) {
                    $state.go("allBlogs", ({searchInput: $scope.searchText}));
                }
            };


            $scope.visible = function () {
                if($state.current != 'home'){
                $('#liAdmin').removeClass('hidden');
                Principal.hasAuthority("ROLE_ADMIN")
                    .then(function (result) {
                        if (result) {
                            $('#liAdmin').removeClass('hidden');
                        } else {
                            $('#liAdmin').addClass('hidden');
                        }
                    });
                }
            };

            $scope.$watch(function () {
                return Principal.isAuthenticated();
            }, function (newValue) {
                $scope.visible();
            });
        }]);

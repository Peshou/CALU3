/**
 * Created by Intel on 02.03.2016.
 */
AngularApp.controller('BlogController', [
    '$scope','Blog','$state', function ($scope,Blog,$state) {
        $scope.blogs = Blog.query();

    }]);
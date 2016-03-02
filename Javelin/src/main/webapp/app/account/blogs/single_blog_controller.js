/**
 * Created by Intel on 02.03.2016.
 */
AngularApp.controller('SingleBlogController', [
    '$scope','Blog','$state', function ($scope,Blog,$state) {
        $scope.blog = Blog.get();
        console.log($state.$stateParams);
    }]);
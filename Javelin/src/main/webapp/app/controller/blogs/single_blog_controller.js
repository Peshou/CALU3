AngularApp.controller('SingleBlogController', [
    '$scope', 'Blog', '$state', '$stateParams', 'BlogPost', function ($scope, Blog, $state, $stateParams, BlogPost) {
        // console.log($stateParams.blogId);

        $scope.blog = Blog.get({id: $stateParams.blogId});

        $scope.posts = BlogPost.getPosts({id: $stateParams.blogId});


        $scope.blId = $stateParams.blogId;
//console.log($stateParams.blogId);
        $scope.addPost = function () {
            $state.go('createPost', {id: $stateParams.blogId});
        }
        console.log($stateParams);
        //console.log($scope.blog);
        //console.log($scope.blog.blogPosts);
    }]);
AngularApp.controller('BlogPostController', [
    '$scope', 'BlogPost', 'Principal', '$stateParams', function ($scope, BlogPost, Principal, $stateParams) {

        Principal.identity().then(function (account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;

            if ($scope.isAuthenticated()) {
                $scope.postMaker = true;
            } else {
                $scope.postMaker = false;
            }
            console.log($scope.isAuthenticated());
            console.log($scope.account);

        });
        $scope.savePost = function () {
            console.log($stateParams.id);
            console.log($scope.post.id);
            console.log($scope.editPost);
            var blogPost = {
                id: $scope.editPost.id,
                name: $scope.editPost.name,
                timeAdded: $scope.editPost.timeAdded,
                text: $scope.editPost.text
            }
            BlogPost.update({id: $stateParams.id, postId: $scope.post.id}, blogPost, function (response) {
                $scope.editMode = false;
            });

        };


        console.log($scope.postMaker);
        $scope.post = BlogPost.get({id: $stateParams.id, postId: $stateParams.blogPostId}, function (response) {
            $scope.editPost = copyPost(response);
        });
        $scope.editPostMode = function () {
            $scope.editMode = true;
        };
        $scope.cancelEditPostMode = function () {
            $scope.editMode = false;
        };
        var copyPost = function (post) {
            return {
                id: post.id,
                name: post.name,
                timeAdded: post.timeAdded,
                text: post.text,
            }
        };
    }]);
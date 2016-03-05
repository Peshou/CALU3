AngularApp.controller('BlogPostController', [
    '$scope', 'BlogPost', 'Principal', '$stateParams', '$http', function ($scope, BlogPost, Principal, $stateParams, $http) {

        Principal.identity().then(function (account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            if ($scope.isAuthenticated()) {
                $http({
                    method: 'GET',
                    url: 'api/blogs/' + $stateParams.id + "/posts/" + $stateParams.blogPostId + "/me"
                }).then(function success(response) {
                    if (response.data == true) {
                        $scope.postMaker = true;


                    } else {
                        $scope.postMaker = false;
                    }
                });
            }
        });
        $scope.savePost = function () {


            console.log($scope.editPost);
            $scope.blogPost = {
                postName: $scope.editPost.name,
                postText: $scope.editPost.text,
                blogId: $stateParams.id
            };
            $http({
                method: 'PUT',
                url: "api/blogs/" + $stateParams.id + "/posts/" + $stateParams.blogPostId,
                data: $scope.blogPost

            }).then(function (response) {
                $scope.editMode = false;
                $scope.editPost.name=$scope.blogPost.postName;
                $scope.editPost.text= $scope.blogPost.postText;
                $scope.post.name=$scope.blogPost.postName;
                $scope.post.text= $scope.blogPost.postText;
            });
        };
        $scope.postComment = function(){
            Principal.identity().then(function (account) {
                $scope.account = account;
                $scope.comment.username=$scope.account.username;
                $scope.comment.blogPostId =$stateParams.blogPostId;
                $http({
                    method: 'POST',
                    url: 'api/comment',
                    data: $scope.comment
                }).then(function success(response) {
                    console.log(response);
                });
            });

        };

        console.log($scope.postMaker);
        $scope.post = BlogPost.get({id: $stateParams.id, postId: $stateParams.blogPostId}, function (response) {
console.log($scope.post);
            $scope.editPost = copyPost(response);
            console.log($scope.editPost);
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
'use strict';

angular.module("snippetShare")
    .controller("UsersShowController", function ($routeParams, User, $scope) {

        //test data for user with :id
        $scope.user = {
            userid: 1,
            username: "Xiaoli",
            email: "xiaoli@gmail.com",
            pwd:"1234",
            profilepic: "software engineering student",
            userAvatarId: 1,
            boards: [
                {
                    bid: 1,
                    title: "board 1",
                    category: "cat1",
                    isPublic: true,
                    createdAt: "",
                    updatedAt: "",
                    description:"board for coding",
                    numOfSnippets: 5,
                    members:[
                        {
                            userid: 2,
                            username: "Alice",
                            userAvatarId: 3
                        },
                        {
                            userid: 3,
                            username: "Tom",
                            userAvatarId: 4
                        }
                    ],
                    requestors:[
                        {
                            userid: 4,
                            username: "Jerry",
                            userAvatarId: 5
                        }
                    ]
                },
                {
                    bid: 2,
                    title: "board 2",
                    category: "cat2",
                    isPublic: true,
                    createdAt: "",
                    updatedAt: "",
                    description:"board for sharing",
                    numOfSnippets: 3,
                    members:[
                        {
                            userid: 3,
                            username: "Tom",
                            userAvatarId: 4
                        }
                    ],
                    requestors:[
                        {
                            userid: 4,
                            username: "Jerry",
                            userAvatarId: 5
                        }
                    ]
                }
            ]
        };


        //request GET all users from server
        //User.find($routeParams.id)
        //    .success(function (data) {
        //        console.log("get user "+ $routeParams.id+ " success");
        //        console.log(data);
        //        $scope.user = data;
        //    });

        $scope.deleteUser = function(user){
            User.remove($routeParams.id)
                .success(function (data) {
                    console.log("delete user "+ $routeParams.id+ " success");
                    console.log(data);
                    $location.path('/users');
                });
        };

    });
